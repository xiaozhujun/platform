package org.whut.platform.business.riskmodel.mapper;

import org.whut.platform.business.riskmodel.entity.RiskModel;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-15
 * Time: 下午12:50
 * To change this template use File | Settings | File Templates.
 */
public interface RiskModelMapper extends AbstractMapper<RiskModel> {
     public List<RiskModel>findByName(String name);
     public List<RiskModel>findByCondition(Map<String,Object> map);
}
