package org.whut.platform.business.riskcolor.mapper;
import org.whut.platform.business.riskcolor.entity.RiskColor;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-14
 * Time: 上午9:25
 * To change this template use File | Settings | File Templates.
 */
public interface RiskColorMapper extends AbstractMapper<RiskColor>{
    public List<RiskColor> list();
    public int setColor(RiskColor riskColor);
    public List<RiskColor> getRiskValueList();

}
