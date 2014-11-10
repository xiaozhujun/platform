package org.whut.rentManagement.business.deptAndEmployee.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.deptAndEmployee.entity.Skill;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午1:46
 * To change this template use File | Settings | File Templates.
 */
public interface SkillMapper extends AbstractMapper<Skill> {
    public Skill getById(long id);
    public List<Skill> list(long appId);
    public String getSkillNameById(long id);
    public long getIdBySkillName(String name);

}
