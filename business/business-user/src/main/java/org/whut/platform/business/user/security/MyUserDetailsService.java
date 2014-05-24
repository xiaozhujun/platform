package org.whut.platform.business.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.whut.platform.business.user.entity.User;
import org.whut.platform.business.user.entity.UserAuthority;
import org.whut.platform.business.user.service.UserAuthorityService;
import org.whut.platform.business.user.service.UserService;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-3-16
 * Time: 下午6:42
 * To change this template use File | Settings | File Templates.
 */
public class MyUserDetailsService implements UserDetailsService {

    private static PlatformLogger logger = PlatformLogger.getLogger(MyUserDetailsService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthorityService userAuthorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByName(username);
        if(user!=null){
            MyUserDetail userDetails = new MyUserDetail();
            userDetails.setUserName(username);
            userDetails.setPassword(user.getPassword());//这里可以从数据库取
            userDetails.setId(user.getId());
            userDetails.setAppId(user.getAppId());
            userDetails.setAuthorities(getUserAuthority(username));
            return userDetails;
        }
        return null;

    }

    //获取用户的授权角色列表
    private List<GrantedAuthority> getUserAuthority(String userName){
        List<UserAuthority> authorityList = userAuthorityService.findByUserName(userName);
        if(authorityList==null) return null;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(UserAuthority userAuthority:authorityList){
            authorities.add(new GrantedAuthorityImpl(userAuthority.getAuthorityName()));
        }
        return authorities;
    }
}
