package org.whut.deviceManagement.business.device.service;

import org.whut.platform.fundamental.logger.PlatformLogger;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 15-4-28
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class DeviceStatusService {
    PlatformLogger logger = PlatformLogger.getLogger(DeviceStatusService.class);

    public void resolveStatus(String message){
        logger.info("DeviceStatusService.resolveStatus: "+message);
    }
}
