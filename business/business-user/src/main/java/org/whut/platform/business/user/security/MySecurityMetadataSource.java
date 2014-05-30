package org.whut.platform.business.user.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.whut.platform.business.user.entity.AuthorityPower;
import org.whut.platform.business.user.service.AuthorityPowerService;

import java.util.*;

//1 加载资源与权限的对应关系
  
/** 
 * 该过滤器的主要作用就是通过spring著名的IoC生成securityMetadataSource。  
 * securityMetadataSource相当于本包中自定义的MyInvocationSecurityMetadataSourceService。  
 * 该MyInvocationSecurityMetadataSourceService的作用提从数据库提取权限和资源，装配到HashMap中，  
 * 供Spring Security使用，用于权限校验。  
 * @author sparta 11/3/29  
 */  
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource,InitializingBean {

    @Autowired
    private AuthorityPowerService authorityPowerServices;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
    private RequestMatcher pathMatcher;

    //由spring调用
    public MySecurityMetadataSource(){

    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
            return new ArrayList<ConfigAttribute>();  
        }    
        
        public boolean supports(Class<?> clazz) {    
            // TODO Auto-generated method stub    
            return true;    
        }    
        //加载所有资源与权限的关系    
        private void loadResourceDefine(){
            if (resourceMap == null) {  
                resourceMap = new HashMap<String, Collection<ConfigAttribute>>();  
                List<AuthorityPower> resources = authorityPowerServices.getAuthorityPowerList();
                 for (AuthorityPower resource : resources) {

                    Collection<ConfigAttribute> configAttributes = null;
                     if(resourceMap.containsKey(resource.getPowerResource())){
                         ConfigAttribute configAttribute = new SecurityConfig(resource.getAuthorityName());
                         configAttributes = (Collection<ConfigAttribute>)resourceMap.get(resource.getPowerResource());
                         configAttributes.add(configAttribute);
                     }else{
                         configAttributes =  new ArrayList<ConfigAttribute>();
                         // 以权限名封装为Spring的security Object
                         //resource.getRoleName() 角色名称 可随意 role_admin  或者 admin
                         ConfigAttribute configAttribute =
                                 new SecurityConfig(resource.getAuthorityName());
                         configAttributes.add(configAttribute);
                         //resource.getInterceptUrl() 格式必须是 拦截的包路径
                         //或者是 比如  /manager/**/*.jh  或者  /system/manager/**/*.jsp
                     }
                    resourceMap.put(resource.getPowerResource(), configAttributes);
                 }  
            }  
                        
        }    
      //返回所请求资源所需要的权限    
        public Collection<ConfigAttribute> getAttributes(Object object)  
                               throws IllegalArgumentException {    
            Iterator<String> it = resourceMap.keySet().iterator();  
            while (it.hasNext()) {  
                String resURL = it.next();  
                Iterator<String> ite = resourceMap.keySet().iterator();  
                pathMatcher = new AntPathRequestMatcher(resURL);  
            if (pathMatcher.matches(((FilterInvocation) object).getRequest())) {  
                    Collection<ConfigAttribute> returnCollection =  
                                          resourceMap.get(resURL);  
                    return returnCollection;  
                }  
            }  
            return null;  
        }

    @Override
    public void afterPropertiesSet() throws Exception {
        loadResourceDefine();
    }
}