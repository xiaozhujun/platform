package org.whut.platform.fundamental.communication.server;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-1-14
 * Time: 下午9:55
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.communication.api.MessageDispatcher;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer implements Runnable{
    /*标识数字*/
    private  int flag = 0;
    /*缓冲区大小*/
    private  int BLOCK = 4096;
    /*接受数据缓冲区*/
    private  ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
    /*发送数据缓冲区*/
    private  ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
    private  Selector selector;

    @Autowired
    private MessageDispatcher messageDispatcher;

    public MessageDispatcher getMessageDispatcher() {
        return messageDispatcher;
    }

    public void setMessageDispatcher(MessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    public NIOServer(){

    }
    // 监听
    public void listen() throws IOException {
        while (true) {
            // 选择一组键，并且相应的通道已经打开
            selector.select();
            // 返回此选择器的已选择键集。
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                handleKey(selectionKey);
            }
        }
    }
    // 处理请求
    private void handleKey(SelectionKey selectionKey) {
        // 接受请求
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String receiveText = null;
        try{
            String sendText;
            int count=0;
            String msgBuffer = "";
            // 测试此键的通道是否已准备好接受新的套接字连接。
            if (selectionKey.isAcceptable()) {
                // 返回为之创建此键的通道。
                server = (ServerSocketChannel) selectionKey.channel();
                // 接受到此通道套接字的连接。
                // 此方法返回的套接字通道（如果有）将处于阻塞模式。
                client = server.accept();
                // 配置为非阻塞
                client.configureBlocking(false);
                // 注册到selector，等待连接
                client.register(selector, SelectionKey.OP_READ);
                System.out.println("socket client accept");
            } else if (selectionKey.isReadable()) {
                // 返回为之创建此键的通道。
                client = (SocketChannel) selectionKey.channel();
                //将缓冲区清空以备下次读取
                receivebuffer.clear();
                //读取服务器发送来的数据到缓冲区中
                count = client.read(receivebuffer);
                if (count > 0) {
                    receiveText = new String( receivebuffer.array(),0,count);
                    System.out.println("服务器端接受客户端数据--:"+receiveText);
                    client.register(selector, SelectionKey.OP_WRITE);
                    //对接受数据的处理
                    msgBuffer +=receiveText;
                    System.out.println("socket client recive: "+receiveText);
                    int startIndex = msgBuffer.indexOf("{sensors:[{");
                    int endIndex = msgBuffer.indexOf("}]}",startIndex);
                    if(endIndex>0){
                        if(startIndex>=0&&endIndex>startIndex){
                            String temp = msgBuffer.substring(startIndex,endIndex+3);
                            if(temp.lastIndexOf("{sensors:[{")>1){
                                temp=temp.substring(temp.lastIndexOf("{sensors:[{"));
                            }
                            msgBuffer = msgBuffer.substring(endIndex+3);
                            System.out.println("socket server parse: "+temp);
                            dealMessageForMongo(temp);
                        }else{
                            msgBuffer = msgBuffer.substring(endIndex+3);
                        }
                    }
                }
            } else if (selectionKey.isWritable()) {
                //将缓冲区清空以备下次写入
                sendbuffer.clear();
                // 返回为之创建此键的通道。
                client = (SocketChannel) selectionKey.channel();
                sendText="message from socket server--" + flag++;
                //向缓冲区中输入数据
                sendbuffer.put(sendText.getBytes());
                //将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
                sendbuffer.flip();
                //输出到通道
                client.write(sendbuffer);
                //System.out.println("服务器端向客户端发送数据--："+sendText);
                client.register(selector, SelectionKey.OP_READ);
            }
        }catch (Exception e){
            if(e instanceof IOException){
                try {
                    client.close();
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
            e.printStackTrace();
        }

    }
    private void dealMessageForMongo(String msgBody){

        try {
            messageDispatcher.dispatchMessage(msgBody);
//            if(sensorDataService.saveMessage(msgBody)==null){
//                System.out.println("ERROR:SAVE FAILED: "+msgBody);
//            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void run(){

            String portString = FundamentalConfigProvider.get("communication.port");
            System.out.println("socket server listen:"+portString);
            int port = Integer.parseInt(portString);
            try{

                // 打开服务器套接字通道
                ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                // 服务器配置为非阻塞
                serverSocketChannel.configureBlocking(false);
                // 检索与此通道关联的服务器套接字
                ServerSocket serverSocket = serverSocketChannel.socket();
                // 进行服务的绑定
                serverSocket.bind(new InetSocketAddress(port));
                // 通过open()方法找到Selector
                selector = Selector.open();
                // 注册到selector，等待连接
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("socket server start----8282:");

                listen();
            }catch (Exception e){}
        }

    /**
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        NIOServer server = new NIOServer();
        server.listen();
    }
}
