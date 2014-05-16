package org.whut.platform.business.user.test;

import org.whut.platform.business.user.security.MD5Encoder;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-3-27
 * Time: 下午6:44
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceWebTest {
    public String parseJsonArray(String jsonString){
        return jsonString.substring(1,jsonString.length()-1);
    }
    public static void main(String args[]){

        System.out.println(MD5Encoder.GetMD5Code("123456"));

        /*UserServiceWebTest userServiceWebTest=new UserServiceWebTest();
        String jsonString="[{\"userId\":118,\"authorityId\":\"1\",\"userName\":\"24242434\",\"authorityName\":\"女\"},{\"userId\":118,\"authorityId\":\"2\",\"userName\":\"24242434\",\"authorityName\":\"\"}]";
        System.out.print(jsonString);
        System.out.println(userServiceWebTest.parseJsonArray(jsonString));*/
    }
}
