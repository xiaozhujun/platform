package org.whut.platform.fundamental.util.tool;

import org.whut.platform.fundamental.util.json.JsonMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-18
 * Time: 上午10:23
 * To change this template use File | Settings | File Templates.
 */
public class ToolUtil {
    public Date transferStringToDate(String str){
        Date d=null;
        String date=null;
        if(str.length()>8){
        try{
            if(str.contains("/")){
            String[] tempDate=str.split("/");
            String year=tempDate[2];
            String month=tempDate[1];
            String day=tempDate[0];
            date=year+"/"+month+"/"+day;
            }else if(str.contains("-")){
            String[] tempDate=str.split("-");
            String year=tempDate[2];
            String month=tempDate[1];
            String day=tempDate[0];
            date=year+"/"+month+"/"+day;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        }
        try{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
            if(date!=null){
            d=sdf.parse(date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return d;
    }
    public String parseAddress(String address){
         String province=null;
         String city=null;
         String area=null;
         if(address.substring(2,3).equals("省")){   //xx省
            province=address.substring(0,2);
            if(address.substring(5,6).equals("市")){                 //xx市
            city=address.substring(3,6);
            area=address.substring(6,9);
            }else if(address.substring(6,7).equals("市")){         //xxx市
            city=address.substring(3,7);
            area=address.substring(7,10);
            }else if(address.substring(7,8).equals("市")){           //xxxx市
            city=address.substring(3,8);
            area=address.substring(8,11);
            }
         }else if(address.substring(3,4).equals("省")){   //xxx省
                province=address.substring(0,3);
             if(address.substring(6,7).equals("市")){                 //xx市
                 city=address.substring(4,7);
                 area=address.substring(7,10);
             }else if(address.substring(7,8).equals("市")){         //xxx市
                 city=address.substring(4,8);
                 area=address.substring(8,11);
             }else if(address.substring(8,9).equals("市")){           //xxxx市
                 city=address.substring(4,9);
                 area=address.substring(9,12);
             }
         }else{
             return "0";
         }
         if(area.substring(2,3).equals("区")){
             return province+","+city+","+area;
         } else{
              return "0";
         }
    }
    public String parseAddress0(String address){
        String province=null;
        String city=null;
        String area=null;
        int cityCount=0;
        if(address==null)
            return "0";
        for(int i=0;i<address.length();i++){
            if(address.substring(i,i+1).equals("市")){
                cityCount++;
            }
        }
        if(address.contains("省")&&address.contains("市")){
        province=address.substring(0,address.indexOf("省"));
        city=address.substring(address.indexOf("省")+1,address.indexOf("市"))+"市";
        if(address.contains("区")&&address.contains("县")||address.contains("区")){
        area=address.substring(address.indexOf("市")+1,address.indexOf("区"))+"区";
        }else if(address.contains("县")){
        area=address.substring(address.indexOf("市")+1,address.indexOf("县"))+"县";
        }else if(!address.contains("县")&&cityCount==2){
        area=address.substring(address.indexOf("市")+1,address.lastIndexOf("市"))+"市";
        }
        return province+","+city+","+area;
        }else{
         return "0";
        }
    }
    public Map<String,String> parseJsonString0(String str){
        Map coordinateMap=new HashMap();
        String[] strArray=str.split("\\{|\\}");
        if(strArray.length<4){
            return null;
        }else{
            String parseStr="{"+strArray[5]+"}";
            HashMap<String,String> jsonObject= JsonMapper.buildNonDefaultMapper().fromJson(parseStr,HashMap.class);
            //JSONObject jsonObject=JSONObject.fromObject(parseStr);
            coordinateMap.put("province",jsonObject.get("province"));
            coordinateMap.put("city",jsonObject.get("city"));
            coordinateMap.put("area",jsonObject.get("district"));
        }
        return coordinateMap;
    }
    /*
    解析下面格式的字串
    {
    "status":"OK",
    "result":{
        "location":{
            "lng":116.421337,
            "lat":39.973869
        },
        "precise":0,
        "confidence":40,
        "level":"\u6559\u80b2"
    }
}
     */
    public Map<String,String> parseJsonString(String str){
        Map coordinateMap=new HashMap();
        String[] strArray=str.split("\\{|\\}");
        if(strArray.length<4){
            return null;
        }else{
        String parseStr="{"+strArray[3]+"}";
        HashMap<String,String> jsonObject= JsonMapper.buildNonDefaultMapper().fromJson(parseStr,HashMap.class);
        //JSONObject jsonObject=JSONObject.fromObject(parseStr);
        coordinateMap.put("lng",jsonObject.get("lng"));
        coordinateMap.put("lat",jsonObject.get("lat"));
        }
        return coordinateMap;
    }
     public static void main(String[] args){
         ToolUtil toolUtil=new ToolUtil();
         System.out.print(toolUtil.parseAddress0("福建省福州市福清市江阴镇下垄村"));

     }
}
