package org.whut.rentManagement.business.contract.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.contract.entity.InstallationDevice;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-12
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public interface InstallationDeviceMapper extends AbstractMapper<InstallationDevice> {
    public List<Map<String,String>> getListByAppId(long appId);
    public List<Map<String,Object>> listByInstallationId(Map<String,Object> condition);
    public void deleteByInstallationId(InstallationDevice installationDevice);
}