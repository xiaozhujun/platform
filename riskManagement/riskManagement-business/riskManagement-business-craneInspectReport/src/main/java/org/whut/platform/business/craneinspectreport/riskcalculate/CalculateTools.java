package org.whut.platform.business.craneinspectreport.riskcalculate;

import org.whut.platform.fundamental.mongo.connector.MongoConnector;
import org.whut.platform.fundamental.util.tool.ToolUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Long getMaxUseTime(String equipmentVariety){  //获取最大使用年限
        return getMaxT(getUseTime(equipmentVariety));
    }
    public List<Long> getUseTime(String equipmentVariety){      //获取使用年限
        List<String> manufactureList=mongoConnector.getOneColumnByEquipmentVariety("manufacturedate",equipmentVariety);
        long usetime=0;
        List<Long> list=new ArrayList<Long>();
        for(String str:manufactureList){
            Date manufacturedate=toolUtil.transferStringToDate(str);
            usetime=DateDiff(manufacturedate);
            list.add(usetime);
        }
        return list;
    }
    public  Float getWorkLevel(){     //获取工作级别
        return null;
    }
    public Float getConclusion(){      //获取检验结果
        return null;
    }
    public Float getMaxRatedLiftWeight(String equipmentVariety){    //获取最大的额定起重量
        List<String> ratedLiftWeightList=mongoConnector.getOneColumnByEquipmentVariety("ratedliftweight",equipmentVariety);
        List<Float> lf=transferListType(ratedLiftWeightList);
        return getMax(lf);
    }
    public Float getMaxSpan(String equipmentVariety){           //获取最大的跨度
        List<String> spanList=mongoConnector.getOneColumnByEquipmentVariety("span",equipmentVariety);
        List<Float> lf=transferListType(spanList);
        return getMax(lf);
    }
    public Float getMaxLiftHeight(String equipmentVariety){         //获取最大的起升高度
        List<String> liftHeightList=mongoConnector.getOneColumnByEquipmentVariety("liftheight",equipmentVariety);
        List<Float> lf=transferListType(liftHeightList);
        return getMax(lf);
    }
    public Float getMaxLiftSpeed(String equipmentVariety){       //获取最大的起升速度
        List<String> liftSpeedList=mongoConnector.getOneColumnByEquipmentVariety("liftspeed",equipmentVariety);
        List<Float> lf=transferListType(liftSpeedList);
        return getMax(lf);
    }
    public Float getMaxRunSpeed(String equipmentVariety){       //获取最大的运行速度
        List<String> runSpeedList=mongoConnector.getOneColumnByEquipmentVariety("runspeed",equipmentVariety);
        List<Float> lf=transferListType(runSpeedList);
        return getMax(lf);
    }
    public Float getMaxCartSpeed(String equipmentVariety){       //获取最大的大车运行速度
        List<String> cartspeedList=mongoConnector.getOneColumnByEquipmentVariety("cartspeed",equipmentVariety);
        List<Float> lf=transferListType(cartspeedList);
        return getMax(lf);

    }
    public Float getMaxCarSpeed(String equipmentVariety){          //获取最大的小车运行速度
        List<String> carspeedList=mongoConnector.getOneColumnByEquipmentVariety("carspeed",equipmentVariety);
        List<Float> lf=transferListType(carspeedList);
        return getMax(lf);
    }
    public Float getMaxLiftMoment(String equipmentVariety){         //获取最大起重力矩
        List<String> liftmomentList=mongoConnector.getOneColumnByEquipmentVariety("liftmoment",equipmentVariety);
        List<Float> lf=transferListType(liftmomentList);
        return getMax(lf);
    }
    public long DateDiff(Date d){          //求当前日期和d日期相差的年数
        Date d1=new Date();
        long t=d1.getTime()-d.getTime()/1000/60/60/24/365;
        return t;
    }
    public Float getMax(List<Float> f){    //List<Float>的冒泡排序
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
    }
    public long getMaxT(List<Long> f){     //List<Long>的冒泡排序
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
    }
    public List<Float> transferListType(List<String> l){
        List<Float> list=new ArrayList<Float>();
        for(String str:l){
            Float f=Float.parseFloat(str);
            list.add(f);
        }
        return list;
    }
}
