package org.whut.rentManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.deptAndEmployee.entity.Skill;
import org.whut.rentManagement.business.deptAndEmployee.mapper.SkillMapper;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-10-10
 * Time: 下午6:56
 * To change this template use File | Settings | File Templates.
 */
public class SkillService {
    @Autowired
    SkillMapper skillMapper;
    public void add(Skill skill){ skillMapper.add(skill);}
    public void delete(Skill skill){
        skillMapper.delete(skill);
    }
    public void update(Skill skill){
        skillMapper.update(skill);
    }
    public Skill getById(long id)
    {
        return skillMapper.getById(id);
    }
}
