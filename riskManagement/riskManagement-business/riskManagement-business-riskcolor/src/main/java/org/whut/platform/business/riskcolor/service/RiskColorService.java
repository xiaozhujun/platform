package org.whut.platform.business.riskcolor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.riskcolor.entity.RiskColor;
import org.whut.platform.business.riskcolor.mapper.RiskColorMapper;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-4-14
 * Time: 上午9:29
 * To change this template use File | Settings | File Templates.
 */
public class RiskColorService {
  @Autowired
  RiskColorMapper riskColorMapper;
  public List<RiskColor> list(){
     return riskColorMapper.list();
  }

  public int setColor(RiskColor riskColor){
      return riskColorMapper.setColor(riskColor);
  }
    public int delete(RiskColor riskColor){
        return riskColorMapper.delete(riskColor);
    }
    public int update(RiskColor riskColor){
        return  riskColorMapper.update(riskColor);
    }
    public List<RiskColor> getRiskValueList()
    {
        return  riskColorMapper.getRiskValueList();
    }
}
