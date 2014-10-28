package org.whut.rentManagement.business.badDebt.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.badDebt.entity.BadDebtDevice;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cwb
 * Date: 14-10-9
 * Time: 下午4:40
 * To change this template use File | Settings | File Templates.
 */
public interface BadDebtDeviceMapper extends AbstractMapper<BadDebtDevice> {
    public int deleteByBadDebtId(long badDebtId); //在BadDebtDeviceMapper.xml中添加deleteByBadDebtId
}
