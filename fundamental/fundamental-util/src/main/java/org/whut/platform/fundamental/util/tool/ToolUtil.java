package org.whut.platform.fundamental.util.tool;

import java.text.SimpleDateFormat;
import java.util.Date;


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
        try{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
         d=sdf.parse(str);
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
         }
         if(area.substring(2,3).equals("区")){
             return province+","+city+","+area;
         } else{
              return null;
         }
    }
     public static void main(String[] args){
         ToolUtil toolUtil=new ToolUtil();
         System.out.print(toolUtil.parseAddress("湖北省武汉市汉阳区建港一路特1号"));

     }

}
