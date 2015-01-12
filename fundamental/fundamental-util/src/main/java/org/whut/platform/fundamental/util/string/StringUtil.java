package org.whut.platform.fundamental.util.string;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 15-1-9
 * Time: 上午11:09
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {

      public static String removeN(String source){    //去除\n
             if(source==null||source.equals(""))
                 return null;
             String target=null;
             if(source.contains("\n")){
                target=source.replaceAll("\n","");
             }else {
                 target=source;
             }
             return target;
      }

      public static void main(String[] args){
          System.out.println(removeN("门式起重机"));
      }

}
