package org.whut.platform.dubboDemo.business.service;

import org.whut.platform.dubboDemo.business.helloworld.HelloworldInterface;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-6-11
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
public class HelloworldService implements HelloworldInterface{
    @Override
    public String hello(String user) {
        return "hello " +user;
    }
}
