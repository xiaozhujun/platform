package org.whut.platform.business.user.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.whut.platform.fundamental.exception.BusinessException;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-5-25
 * Time: 上午12:49
 * To change this template use File | Settings | File Templates.
 */
public class UserContext {
    public static MyUserDetail  currentUser(){
        Object credential = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(credential instanceof UserDetails){
            return (MyUserDetail)credential;
        }else{
            throw new BusinessException(new Exception("用户初始化异常"));
        }
    }

    public static String  currentUserName(){
        MyUserDetail myUserDetail = currentUser();
        if(myUserDetail!=null){
            return myUserDetail.getUserName();
        }
        return null;
    }

    public static Long  currentUserId(){
        MyUserDetail myUserDetail = currentUser();
        if(myUserDetail!=null){
            return myUserDetail.getId();
        }
        return null;
    }

    public static Long  currentUserAppId(){
        MyUserDetail myUserDetail = currentUser();
        if(myUserDetail!=null){
            return myUserDetail.getAppId();
        }
        return null;
    }
}
