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
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class NIOServer implements Runnable{

    public static final PlatformLogger logger = PlatformLogger.getLogger(NIOServer.class);
    public static HashMap<SocketChannel,StringBuffer> msgBufferMap = new HashMap<SocketChannel, StringBuffer>();

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

    /**
     *获得当前key的消息缓冲
     * @param key
     * @return
     */
    public StringBuffer getMsgBuffer(SocketChannel key){
        StringBuffer msgBuffer = msgBufferMap.get(key);
        if(msgBuffer==null){
            msgBuffer = new StringBuffer();
            msgBufferMap.put(key,msgBuffer);
        }
        return msgBuffer;
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
                logger.info("socket client accept");
            } else if (selectionKey.isReadable()) {
                // 返回为之创建此键的通道。
                client = (SocketChannel) selectionKey.channel();
                //将缓冲区清空以备下次读取
                receivebuffer.clear();
                //读取服务器发送来的数据到缓冲区中
                count = client.read(receivebuffer);
                if (count > 0) {
                    receiveText = new String( receivebuffer.array(),0,count);
                    if(receiveText!=null){
                        logger.info("receive : " + receiveText);
                        resolveMessage(receiveText,client);
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
             try{
                 messageDispatcher.exceptionProcess();
                 client.close();
            }catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
            }
            e.printStackTrace();
    }
    }
    /**
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
//       NIOServer server = new NIOServer();
//       server.listen();
         String data = "{sensors:[{sNum:2100000000010000,Freq:24000,Samp:512,Time:'2014-06-27 15:12:48',Data:[-73,-73,-73,-73,-74,-74,-75,-75,-76,-77,-77,-77,-77,-77,-77,-77,-76,-75,-75,-74,-73,-73,-72,-72,-72,-73,-73,-74,-75,-75,-76,-76,-77,-77,-77,-77,-77,-76,-76,-75,-75,-74,-74,-73,-73,-73,-73,-73,-74,-74,-75,-76,-77,-78,-79,-79,-79,-80,-80,-79,-78,-76,-75,-73,-72,-71,-69,-68,-68,-68,-69,-70,-71,-73,-75,-77,-79,-81,-82,-83,-83,-83,-83,-82,-80,-78,-76,-75,-73,-72,-70,-69,-68,-68,-68,-69,-70,-72,-73,-75,-76,-78,-79,-80,-81,-81,-82,-81,-80,-79,-78,-76,-75,-73,-72,-71,-71,-70,-70,-71,-72,-72,-74,-75,-76,-77,-78,-79,-79,-79,-79,-79,-78,-77,-76,-75,-74,-74,-73,-72,-72,-72,-72,-73,-73,-74,-75,-75,-76,-77,-77,-77,-77,-77,-77,-76,-75,-75,-74,-74,-74,-73,-73,-74,-74,-75,-75,-76,-76,-77,-78,-78,-78,-78,-78,-77,-77,-76,-75,-74,-73,-73,-72,-72,-71,-71,-71,-71,-71,-72,-72,-73,-74,-75,-75,-76,-76,-77,-77,-76,-76,-76,-75,-75,-75,-75,-74,-74,-74,-74,-75,-75,-75,-75,-75,-76,-76,-76,-76,-75,-75,-75,-74,-74,-73,-72,-71,-71,-71,-71,-71,-72,-72,-74,-75,-77,-78,-79,-80,-81,-81,-81,-81,-81,-80,-78,-77,-75,-74,-72,-71,-70,-69,-68,-68,-68]},{sNum:2100000000010010,Freq:24000,Samp:512,Time:'2014-06-27 15:12:48',Data:[-75,-75,-75,-75,-76,-75,-75,-75,-75,-75,-75,-75,-75,-75,-75,-75,-75,-74,-74,-74,-74,-74,-74,-74,-74,-74,-74,-75,-75,-75,-75,-75,-75,-75,-75,-74,-74,-73,-72,-72,-72,-72,-72,-72,-72,-72,-73,-73,-73,-74,-74,-73,-73,-73,-73,-73,-73,-73,-72,-72,-72,-73,-73,-73,-73,-73,-74,-74,-74,-74,-74,-74,-73,-73,-73,-73,-72,-72,-72,-72,-72,-72,-72,-72,-72,-73,-73,-73,-73,-73,-74,-74,-75,-74,-74,-74,-74,-73,-72,-72,-72,-71,-71,-71,-72,-72,-73,-73,-74,-75,-75,-75,-75,-75,-75,-74,-74,-74,-74,-74,-73,-73,-73,-73,-72,-72,-72,-72,-72,-72,-72,-72,-73,-73,-74,-74,-75,-75,-75,-76,-76,-75,-75,-75,-74,-74,-73,-72,-72,-72,-72,-72,-73,-73,-73,-73,-73,-73,-72,-72,-72,-72,-72,-72,-72,-72,-73,-73,-73,-73,-74,-73,-73,-73,-74,-73,-73,-73,-73,-73,-73,-73,-74,-74,-74,-74,-74,-74,-75,-75,-74,-75,-74,-74,-74,-74,-74,-74,-74,-74,-74,-74,-74,-75,-75,-75,-75,-75,-75,-75,-75,-75,-75,-75,-75,-76,-76,-76,-76,-76,-76,-76,-76,-75,-75,-75,-75,-74,-74,-74,-74,-74,-74,-74,-74,-74,-74,-74,-73,-73,-73,-73,-73,-74,-74,-74,-75,-75,-75,-76,-76,-76,-76,-76,-75,-75]}]}";
        int startIndex = data.indexOf("{sensors:[{");
        int endIndex = data.indexOf("}]}");
        System.out.println(data.substring(0,endIndex+3));

        StringBuffer buffer = new StringBuffer("");
        buffer.append(data);
        System.out.println("1:"+buffer.toString());

        buffer.setLength(0);
        System.out.println("2:"+buffer.toString());

    }

    private void resolveMessage(String msg,SocketChannel key){
        //client.register(selector, SelectionKey.OP_WRITE);
        //对接受数据的处理
        StringBuffer msgBuffer = getMsgBuffer(key);
        String localMsg = msg;
        while (true){
            int startIndex = localMsg.indexOf("{"+FundamentalConfigProvider.get("message.nioServer.type")+":[{");
            int endIndex = localMsg.indexOf("}]}",startIndex);
            if(endIndex>=0&&startIndex>=0){
                if(endIndex<startIndex){
                    if(msgBuffer.length()>0){
                        logger.info("text1:append: "+msg.substring(0,endIndex+3));
                        msgBuffer.append(msg.substring(0,endIndex+3));
                        messageDispatcher.dispatchMessage(msgBuffer.toString());
                        msgBuffer.setLength(0);
                    }
                    localMsg = localMsg.substring(startIndex);
                }else{
                    logger.info("text2:append: "+localMsg.substring(startIndex,endIndex+3));
                    messageDispatcher.dispatchMessage(localMsg.substring(startIndex,endIndex+3));
                    if(msgBuffer.length()>0){
                        msgBuffer.setLength(0);
                    }
                    localMsg = localMsg.substring(endIndex+3);
                }
            }else if(endIndex>=0&&startIndex<0){
                if(msgBuffer.length()>0){
                    logger.info("text3:append: "+msg.substring(0,endIndex+3));
                    msgBuffer.append(msg.substring(0,endIndex+3));
                    messageDispatcher.dispatchMessage(msgBuffer.toString());
                    msgBuffer.setLength(0);
                }
                break;
            }else if(endIndex<0&&startIndex>=0){
                if(msgBuffer.length()>0){
                    msgBuffer.setLength(0);
                }
                msgBuffer.append(localMsg.substring(startIndex));
                break;
            }else {
                if(msgBuffer.length()>0){
                    msgBuffer.append(localMsg);
                }
                break;
            }

        }

    }

    @Override
    public void run(){

            String portString = FundamentalConfigProvider.get("communication.port");
            logger.info("socket server listen:"+portString);
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
                logger.info("socket server start----8282:");

                listen();
            }catch (Exception e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }


}
