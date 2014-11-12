package org.whut.rentManagement.business.contract.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.contract.entity.InstallationDevice;
import org.whut.rentManagement.business.contract.mapper.InstallationDeviceMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-12
 * Time: 下午6:49
 * To change this template use File | Settings | File Templates.
 */
public class InstallationDeviceService {

    @Autowired
    private InstallationDeviceMapper installationDeviceMapper;
    public void add(InstallationDevice installationDevice){
        installationDeviceMapper.add(installationDevice);
    }
    public List<Map<String,String>> getListByAppId(long appId){
        return installationDeviceMapper.getListByAppId(appId);
    }
    public int update(InstallationDevice installationDevice){
        return installationDeviceMapper.update(installationDevice);
    }
    public int delete(InstallationDevice installationDevice){
        return installationDeviceMapper.delete(installationDevice);
    }
    public List<Map<String,Object>> listByInstallationId(Map<String,Object> condition){
        return installationDeviceMapper.listByInstallationId(condition);
    }
    public void deleteByInstallationId(InstallationDevice installationDevice){
        installationDeviceMapper.deleteByInstallationId(installationDevice);
    }
}
