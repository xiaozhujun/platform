package org.whut.platform.fundamental.map;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.util.tool.ToolUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-29
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class BaiduMapUtil {
    /**
     * @param addr
     * 查询的地址
     * @return
     * @throws IOException
     */
    public Map<String, String> getCoordinate(String addr) throws IOException {
        String address = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String key = FundamentalConfigProvider.get("key");
        String url = String
                .format(FundamentalConfigProvider.get("url"),
                        address, key);
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String dataInfo="";
                String data = null;
                while((data= br.readLine())!=null){
                    dataInfo+=data;
                }
                ToolUtil toolUtil=new ToolUtil();
                map=toolUtil.parseJsonString(dataInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(insr!=null){
                insr.close();
            }
            if(br!=null){
                br.close();
            }
        }
        return map;
    }
    public static void main(String[] args) throws IOException {
        BaiduMapUtil baiduMapUtil = new BaiduMapUtil();
        Map map=baiduMapUtil.getCoordinate("湖北省武汉市江岸区赵家条319号");
        System.out.print(map.get("lng")+""+map.get("lat"));
    }
}

