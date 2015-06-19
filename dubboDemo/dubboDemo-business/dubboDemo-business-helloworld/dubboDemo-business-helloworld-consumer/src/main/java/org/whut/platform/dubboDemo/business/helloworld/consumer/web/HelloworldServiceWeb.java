package org.whut.platform.dubboDemo.business.helloworld.consumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.whut.platform.dubboDemo.business.helloworld.HelloworldInterface;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-6-11
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
@Component
@Path("/helloworld")
public class HelloworldServiceWeb {

    @Autowired
    HelloworldInterface helloworldInterface;

    @Produces(MediaType.APPLICATION_JSON+";charset=UTF-8")
    @Path("/hello/{user}")
    @GET
    public String importPersonnelConfiguration(@PathParam("user") String user){
       return helloworldInterface.hello(user);
    }
}
