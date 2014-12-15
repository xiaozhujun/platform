package org.whut.platform.business.craneinspectreport.riskcalculate;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.tool.ToolUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-5-15
 * Time: 下午5:12
 * To change this template use File | Settings | File Templates.
 */
public class CalculateTools {
    private MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportCollection");
    private ToolUtil toolUtil=new ToolUtil();
    private static List<List<DBObject>> dbObjectList=new ArrayList<List<DBObject>>();
    public void getDbArrayListFromMongo(){
           dbObjectList= mongoConnector.getDbArrayListFromMongo();
    }
    public List<String> getOneColumnByEquipmentVariety(String column,List<String> equipmentVariety){
        List<String> list=new ArrayList<String>();
            for(String equipment:equipmentVariety){
                for(List<DBObject> dd:dbObjectList){
                    for(DBObject ddd:dd){
                    if(ddd.get("equipmentvariety").equals(equipment)){
                        list.add((String)ddd.get(column));
                    }
                    }
                }
            }
        return list;
    }
    public Long getMaxUseTime(List<String> equipmentVariety){  //获取最大使用年限
        return getMaxT(getUseTime(equipmentVariety));
    }
    public List<Long> getUseTime(List<String> equipmentVariety){      //获取使用年限
        List<String> manufactureList=new ArrayList<String>();
        manufactureList=getOneColumnByEquipmentVariety(WeightFactor.manufacturedate, equipmentVariety);
        long usetime=0;
        List<Long> list=new ArrayList<Long>();
        for(String str:manufactureList){
            if(str!=null){
            Date manufacturedate=toolUtil.transferStringToDate(str);
            usetime=DateDiff(manufacturedate);
            list.add(usetime);
            }else{
            list.add(0L);
            }
        }
        return list;
    }
    public long getUseTime(String str){
        long useTime=0;
        if(str!=null){
        Date manufacturedate=toolUtil.transferStringToDate(str);
        useTime=DateDiff(manufacturedate);
        }
        return useTime;
    }
    public  Float getWorkLevel(String workLevel){     //获取工作级别
        Float f=null;
        for(int i=1;i<=8;i++){
            String w="A"+i;
            if(workLevel.equals(w)){
               f=(float)(i+2);
            }
        }
        return f;
    }
    public Float getConclusion(String conclusion){      //获取检验结果
        Float f=null;
        if(conclusion.equals(WeightFactor.qualified)){
            f=0f;
        }else if(conclusion.equals(WeightFactor.reInspectQualified)){
            f=10f;
        }else if(conclusion.equals(WeightFactor.unqualified)){
            f=100f;
        }
        return f;
    }
    public Float getMaxRatedLiftWeight(List<String> equipmentVariety){    //获取最大的额定起重量
        List<String> ratedLiftWeightList=getOneColumnByEquipmentVariety(WeightFactor.ratedLiftWeight, equipmentVariety);
        List<Float> lf=transferListType(ratedLiftWeightList);
        return getMax(lf);
    }
    public Float getMaxSpan(List<String> equipmentVariety){           //获取最大的跨度
        List<String> spanList=getOneColumnByEquipmentVariety(WeightFactor.span, equipmentVariety);
        List<Float> lf=transferListType(spanList);
        return getMax(lf);
    }
    public Float getMaxRange(List<String> equipmentVariety){
        List<String> rangeList=getOneColumnByEquipmentVariety(WeightFactor.range, equipmentVariety);
        List<Float> lf=transferListType(rangeList);
        return getMax(lf);
    }
    public Float getMaxLiftHeight(List<String> equipmentVariety){         //获取最大的起升高度
        List<String> liftHeightList=getOneColumnByEquipmentVariety(WeightFactor.liftHeight, equipmentVariety);
        List<Float> lf=transferListType(liftHeightList);
        return getMax(lf);
    }
    public Float getMaxLiftSpeed(List<String> equipmentVariety){       //获取最大的起升速度
        List<String> liftSpeedList=getOneColumnByEquipmentVariety(WeightFactor.liftSpeed, equipmentVariety);
        List<Float> lf=transferListType(liftSpeedList);
        return getMax(lf);
    }
    public Float getMaxRunSpeed(List<String> equipmentVariety){       //获取最大的运行速度
        List<String> runSpeedList=getOneColumnByEquipmentVariety(WeightFactor.runSpeed, equipmentVariety);
        List<Float> lf=transferListType(runSpeedList);
        return getMax(lf);
    }
    public Float getMaxCartSpeed(List<String> equipmentVariety){       //获取最大的大车运行速度
        List<String> cartspeedList=getOneColumnByEquipmentVariety(WeightFactor.cartSpeed, equipmentVariety);
        List<Float> lf=transferListType(cartspeedList);
        return getMax(lf);

    }
    public Float getMaxCarSpeed(List<String> equipmentVariety){          //获取最大的小车运行速度
        List<String> carspeedList=getOneColumnByEquipmentVariety(WeightFactor.carSpeed, equipmentVariety);
        List<Float> lf=transferListType(carspeedList);
        return getMax(lf);
    }
    public Float getMaxLiftMoment(List<String> equipmentVariety){         //获取最大起重力矩
        List<String>liftmomentList=getOneColumnByEquipmentVariety(WeightFactor.maxLiftMoment, equipmentVariety);
        List<Float> lf=transferListType(liftmomentList);
        return getMax(lf);
    }
    public long DateDiff(Date d){          //求当前日期和d日期相差的年数
        Date d1=new Date();
        if(d!=null){
        long t=d1.getTime()-d.getTime()/1000/60/60/24/365;
        return t;
        }else
        return 0L;
    }
    public Float getMax(List<Float> f){    //List<Float>的冒泡排序
        if(f!=null&&f.size()!=0){
        for(int i=0;i<f.size()-1;i++){
            for(int j=0;j<f.size()-i-1;j++){
                if(f.get(j)>f.get(j+1)){
                    Float temp=f.get(j);
                    f.set(j,f.get(j+1));
                    f.set(j+1,temp);
                }
            }
        }
        return f.get(f.size()-1);
        }else{
            return null;
        }
    }
    public long getMaxT(List<Long> f){     //List<Long>的冒泡排序
        if(f.size()!=0){
        for(int i=0;i<f.size()-1;i++){
            for(int j=0;j<f.size()-i-1;j++){
                if(f.get(j)>f.get(j+1)){
                    Long temp=f.get(j);
                    f.set(j,f.get(j+1));
                    f.set(j+1,temp);
                }
            }
        }
        return f.get(f.size()-1);
        }else{
            return 0;
        }
    }
    public List<Float> transferListType(List<String> l){
        if(l.size()!=0){
        List<Float> list=new ArrayList<Float>();
        for(String str:l){
            if(filter(str,"^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?$")){
                Float f=Float.parseFloat(str);
                list.add(f);
            }else{

            }
        }
        return list;
        }else{
            return null;
        }
    }
    public boolean filter(String str,String pattern){
          boolean b=false;
          if(str!=null){
          if(str.length()<5){
          String regex=pattern;
          Pattern p=Pattern.compile(regex);
          Matcher m=p.matcher(str);
          while (m.find()){
             b=true;
          }
          }
          }
        return b;
    }
    public  static void main(String[] args){
           CalculateTools tools=new CalculateTools();
           System.out.println(tools.filter(null,"^[+-]?([0-9]*\\.?[0-9]+|[0-9]+\\.?[0-9]*)([eE][+-]?[0-9]+)?$"));
    }
    public Float getAverageRiskValue(List<Float> riskValueList){
          Float sum=0f;
          if(riskValueList!=null||riskValueList.size()!=0){
          for(Float riskValue:riskValueList){
                sum+=riskValue;
          }
          Float average=sum/riskValueList.size();
          return average;
          }else{
              return null;
          }
    }
}
