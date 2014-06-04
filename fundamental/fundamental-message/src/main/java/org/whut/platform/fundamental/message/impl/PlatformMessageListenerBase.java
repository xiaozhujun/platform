package org.whut.platform.fundamental.message.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.message.api.PlatformMessageListener;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-6-4
 * Time: 下午8:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class PlatformMessageListenerBase implements PlatformMessageListener,InitializingBean{
    @Autowired
    PlatformMessageDistributer distributer;

    public PlatformMessageDistributer getDistributer() {
        return distributer;
    }

    public void setDistributer(PlatformMessageDistributer distributer) {
        this.distributer = distributer;
    }

    @Override
    public void afterPropertiesSet(){
        if(this.distributer!=null){
            distributer.addListener(this);
        }
    }


}
