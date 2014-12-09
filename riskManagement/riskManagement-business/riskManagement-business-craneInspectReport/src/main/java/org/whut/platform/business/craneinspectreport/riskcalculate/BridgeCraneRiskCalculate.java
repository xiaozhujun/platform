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
        Float R=0f;
        Float P1=0f;
        Float P2=0f;
        Float P3=0f;
        Float P4=0f;
        Float P5=0f;
        Float P6=0f;
        Float P7=0f;
        Float P8=0f;
        Float P9=0f;
        Float S1=0f;
        Float S2=0f;
        Float S3=0f;
        Float ap=0f;
        Float P=0f;
        Float S=0f;
        if(d!=null&&craneInspectReport!=null){
        if(transferMaxValueToFloat(d,WeightFactor.maxUseTime)!=null&&craneInspectReport.getUseTime()!=0){
        P1=craneInspectReport.getUseTime()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxUseTime);
        }
        if(craneInspectReport.getWorkLevel()!=null){
        P2=calculateTools.getWorkLevel(craneInspectReport.getWorkLevel());
        }
        if(craneInspectReport.getConclusion()!=null){
        P3=calculateTools.getConclusion(craneInspectReport.getConclusion());
        }
        if(filter(String.valueOf(transferMaxValueToFloat(d,WeightFactor.maxRatedLiftWeight)))&&filter(craneInspectReport.getRatedLiftWeight())){
        P4=Float.parseFloat(craneInspectReport.getRatedLiftWeight())*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxRatedLiftWeight);
        }
        if(filter(String.valueOf(transferMaxValueToFloat(d,WeightFactor.maxLiftSpeed)))&&filter(String.valueOf(craneInspectReport.getLiftSpeed()))){
        P5=craneInspectReport.getLiftSpeed()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxLiftSpeed);
        }
        if(filter(String.valueOf(transferMaxValueToFloat(d,WeightFactor.maxLiftHeight)))&&filter(String.valueOf(craneInspectReport.getLiftHeight()))){
        P6=craneInspectReport.getLiftHeight()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxLiftHeight);
        }
        //不同的元素
        if(filter(String.valueOf(transferMaxValueToFloat(d,WeightFactor.maxSpan)))&&filter(String.valueOf(craneInspectReport.getSpan()))){
        P7=craneInspectReport.getSpan()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxSpan);
        }
        if(filter(String.valueOf(transferMaxValueToFloat(d,WeightFactor.maxCartSpeed)))&&filter(String.valueOf(craneInspectReport.getCartSpeed()))){
        P8=craneInspectReport.getCartSpeed()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxCartSpeed);
        }
        if(filter(String.valueOf(transferMaxValueToFloat(d,WeightFactor.maxCarSpeed)))&&filter(String.valueOf(craneInspectReport.getCarSpeed()))){
        P9=craneInspectReport.getCarSpeed()*WeightFactor.constant/transferMaxValueToFloat(d,WeightFactor.maxCarSpeed);
        }
        S1=P6*P7/WeightFactor.constant;
        S2=P4*S1/WeightFactor.constant;
        S3=1f;
        if(P1!=null&P2!=null&&P3!=null&&P4!=null&&P5!=null&&P6!=null&&P7!=null&&P8!=null&&P9!=null){
        ap=WeightFactor.alpha1*P1+WeightFactor.alpha2*P2+WeightFactor.alpha3*P3+WeightFactor.alpha4*P4+WeightFactor.alpha5*P5+WeightFactor.alpha6*P6+WeightFactor.alpha7*P7+WeightFactor.alpha8*P8+WeightFactor.alpha9*P9;
        }
        P=WeightFactor.f1*WeightFactor.f2*WeightFactor.f3*WeightFactor.f4*ap;
        S=WeightFactor.beta1*S1+WeightFactor.beta2*S2+WeightFactor.beta3*S3;
        R=P*S;
        }
        return R;
    }
    public DBObject getMaxValue(String craneTypeId){
        MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportMaxValue");
        return mongoConnector.getMaxValueByCraneType(craneTypeId);
    }
    public Float transferMaxValueToFloat(DBObject d,String value){
        if(d!=null&&filter((String)d.get(value))){
        return Float.parseFloat((String)d.get(value));
        }
        return null;
    }
    public boolean filter(String condition){
        boolean f=false;
        if(calculateTools.filter(condition,"^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?$")){
            f=true;
        }
        return f;
    }
}
