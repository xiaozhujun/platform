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
    //根据经纬度求出半径范围内最大最小经纬度
    public Map<String,Double> getAround(double lat,double lon,double radius){
        double PI=3.14159265;
        double EARTH_RADIUS=6378137;
        double RAD=Math.PI/180.0;
        double latitude=lat;
        double longitude=lon;
        double degree =(24901*1609)/360.0;
        double raidusMile=radius;
        double dpmLat=1/degree;
        double radiusLat=dpmLat*raidusMile;
        double minLat=latitude-radiusLat;
        double maxLat=latitude+radiusLat;
        double mpdLng=degree*Math.cos(latitude*(PI/180));
        double dpmLng=1/mpdLng;
        double radiusLng=dpmLng*raidusMile;
        double minLng=longitude-radiusLng;
        double maxLng=longitude+radiusLng;
        //System.out.println("["+minLat+","+minLng+","+maxLat+","+maxLng+"]");
        Map<String,Double> map=new HashMap<String,Double>();
        map.put("maxLng",maxLng);
        map.put("maxLat",maxLat);
        map.put("minLng",minLng);
        map.put("minLat",minLat);
        return map;
    }
    public Map<String,String> parseAddressToGetProvinceCityArea(String lng,String lat) throws IOException{
        Map<String, String> map = new HashMap<String, String>();
        String location=lat+","+lng;
        String key = FundamentalConfigProvider.get("key");
        String url = String
                .format(FundamentalConfigProvider.get("addressUrl"),
                        key,location);
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
                // System.out.println(dataInfo);
                ToolUtil toolUtil=new ToolUtil();
                map=toolUtil.parseJsonString0(dataInfo);
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
    public Map<String,String> parseAddToProCityArea(String add){
        Map<String,String> m=new HashMap<String, String>();
        try{
            Map map=getCoordinate(add);
            if(map!=null&&map.get("lng")!=null&&map.get("lat")!=null){
                m=parseAddressToGetProvinceCityArea(map.get("lng").toString(), map.get("lat").toString());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
    public static void main(String[] args) throws IOException {
       BaiduMapUtil baiduMapUtil = new BaiduMapUtil();
       /*Map map=baiduMapUtil.getCoordinate("中国台湾");
        System.out.print(map.get("lng")+","+map.get("lat"));*/
       /* Map map=baiduMapUtil.getAround(30.857224,114.587311,6621.525348039403);
        System.out.print(map.get("maxLng")+","+map.get("maxLat")+","+map.get("minLng")+","+map.get("minLat"));*/
        System.out.println(baiduMapUtil.parseAddToProCityArea("湖北省武汉市东西湖区田园路1196号"));
    }
}

