package org.whut.platform.business.craneinspectreport.riskcalculate;

import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-15
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public interface ICalculateRisk {
    public Float calculateRisk(CraneInspectReport craneInspectReport,String craneType);
}
