package org.whut.platform.business.craneinspectreport.riskcalculate;

import com.mongodb.DBObject;
import org.whut.platform.business.craneinspectreport.entity.CraneInspectReport;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-15
 * Time: 下午4:46
 * To change this template use File | Settings | File Templates.
 */
public class BridgeCraneRiskCalculate implements ICalculateRisk{
    private CalculateTools calculateTools=new CalculateTools();
    public Float calculateRisk(CraneInspectReport craneInspectReport,String craneType){
        DBObject d=getMaxValue(craneType);
        Float P1=craneInspectReport.getUseTime()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxUseTime);
        Float P2=calculateTools.getWorkLevel(craneInspectReport.getWorkLevel());
        Float P3=calculateTools.getConclusion(craneInspectReport.getConclusion());
        Float P4=Float.parseFloat(craneInspectReport.getRatedLiftWeight())*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxRatedLiftWeight);
        Float P5=craneInspectReport.getLiftSpeed()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxLiftSpeed);
        Float P6=craneInspectReport.getLiftHeight()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxLiftHeight);
        //不同的元素
        Float P7=craneInspectReport.getSpan()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxSpan);
        Float P8=craneInspectReport.getCartSpeed()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxCartSpeed);
        Float P9=craneInspectReport.getCarSpeed()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxCarSpeed);
        Float S1=P6*P7/WeightFactor.constant;
        Float S2=P4*S1/WeightFactor.constant;
        Float S3=1f;
        Float ap=WeightFactor.alpha1*P1+WeightFactor.alpha2*P2+WeightFactor.alpha3*P3+WeightFactor.alpha4*P4+WeightFactor.alpha5*P5+WeightFactor.alpha6*P6+WeightFactor.alpha7*P7+WeightFactor.alpha8*P8+WeightFactor.alpha9*P9;
        Float P=WeightFactor.f1*WeightFactor.f2*WeightFactor.f3*WeightFactor.f4*ap;
        Float S=WeightFactor.beta1*S1+WeightFactor.beta2*S2+WeightFactor.beta3*S3;
        Float R=P*S;
        return R;
    }
    public DBObject getMaxValue(String craneTypeId){
        MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportMaxValue");
        return mongoConnector.getMaxValueByCraneType(craneTypeId);
    }
    public Float transferMaxValueToFloat(DBObject d,String value){
          return Float.parseFloat((String)d.get(value));
    }
}
