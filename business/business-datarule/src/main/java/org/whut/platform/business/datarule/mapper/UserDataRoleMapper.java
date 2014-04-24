package org.whut.platform.business.datarule.mapper;

import org.whut.platform.business.datarule.entity.UserDataRole;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-21
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */

public interface UserDataRoleMapper extends AbstractMapper<UserDataRole>{
    public List<String> findDataRoleByUserName(String userName);
    public int deleteByUserName(String userName);
}
