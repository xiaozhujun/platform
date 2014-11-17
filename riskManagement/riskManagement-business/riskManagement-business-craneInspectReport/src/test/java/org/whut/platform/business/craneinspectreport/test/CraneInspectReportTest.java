package org.whut.platform.business.craneinspectreport.test;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.whut.platform.business.craneinspectreport.riskcalculate.CalculateTools;
import org.whut.platform.fundamental.jxl.model.ExcelMap;
import org.whut.platform.fundamental.jxl.utils.JxlExportImportUtils;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-18
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
public class CraneInspectReportTest {
    private JxlExportImportUtils jxlExportImportUtils;
    private ExcelMap excelMap=new ExcelMap();
    private CalculateTools calculateTools=new CalculateTools();
    private static List<List<DBObject>> dbObjectList=new ArrayList<List<DBObject>>();
    public String getDocumentJson(String path){
        File f=new File(path);
        excelMap=jxlExportImportUtils.analysisExcel(f);
        String documentJson="{craneinspectreports:[";
        for(int i=0;i<excelMap.getContents().size()-1;i++){
           String documentJson1="{";
            for(int j=0;j<excelMap.getContents().get(i).size()-1;j++){
                documentJson1+=excelMap.getHeads().get(j)+":"+excelMap.getContents().get(i).get(j)+",";
                 if(j+1==excelMap.getContents().get(i).size()-1){
                     documentJson1+=excelMap.getHeads().get(j+1)+":"+excelMap.getContents().get(i).get(j+1);
                 }
            }
            documentJson+=documentJson1+"},";
            if(i+1==excelMap.getContents().size()-1){
                documentJson+=documentJson1+"}";
            }
        }
        documentJson+="]}";
        return documentJson;
    }
    public void parseExcel(String path){
           File file=new File(path);
           excelMap=jxlExportImportUtils.analysisExcel(file);
           for(int i=0;i<excelMap.getContents().size();i++){
               System.out.print(excelMap.getContents().get(i).get(0)+"test");
               for(int j=0;j<excelMap.getContents().get(i).size();j++){
                   System.out.print(excelMap.getContents().get(i).get(j));
               }
           }
    }
    public void getJsonDocument(){
        MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportCollection");
       /* DBObject d=mongoConnector.getDBObjectByReportNumber("71795760-0#2013-QZD0024");
        System.out.println(d.get("unitaddress"));*/
       /* List<String> d=mongoConnector.getOneColumn("reportnumber");
        for(String s:d){
            System.out.println(s);
        }*/
    }
    public List<Long>getCraneTypeByCraneInspectReportInfo(){
        List<Long>list=new ArrayList<Long>();
        list.add(1l);
        list.add(2l);
        list.add(3l);
        list.add(4l);
        return list;
    }
    public List<String> getEquipmentVarietyByCraneType(long id){
        List<String> list=new ArrayList<String>();
        if(id==1){
            list.add("门座起重机");
            list.add("固定式起重机");
            list.add("门座式起重机");
            list.add("港口门座起重机");
        }else if(id==2){
            list.add("通用门式起重机");
            list.add("电动葫芦门式起重机");
            list.add("门式起重机");
            list.add("电动门葫芦门式起重机");
            list.add("轨道式集装箱门式起重机");
            list.add("岸边集装箱起重机");
            list.add("岸边式集装箱起重机");
            list.add("轮胎式龙门起重机");
            list.add("半门式起重机");
            list.add("轮胎式龙门起重机");
            list.add("轮胎式门式起重机");
        }else if(id==3){
            list.add("汽车起重机");
            list.add("履带起重机");
            list.add("集装箱正面吊运起重机");
            list.add("轮胎起重机");
            list.add("轮胎式起重机");
            list.add("汽车吊");
            list.add("履带式起重机");
        }else if(id==4){
            list.add("电动葫芦桥式起重机");
            list.add("冶金桥式起重机");
        }
        return list;
    }
    public String getCraneInspectReportMaxValue(){
        List<Long> craneTypeIdList=getCraneTypeByCraneInspectReportInfo();
        //documentJson={maxValue:[{"typeId":"1","maxusetime":"maxusetime",...},{"typeId":"2","maxusetime":"maxusetime",...}]}
        String documentJson="{maxValue:[";
        int i=0;
        for(Long typeId:craneTypeIdList){
            List<String> equipmentVariety=getEquipmentVarietyByCraneType(typeId);
            documentJson+="{typeId:'"+typeId+"',";
            String maxUseTime=null;
            String maxRatedLiftWeight=null;
            String maxSpan=null;
            String maxRange=null;
            String maxLiftHeight=null;
            String maxLiftSpeed=null;
            String maxRunSpeed=null;
            String maxCartSpeed=null;
            String maxCarSpeed=null;
            String maxLiftMoment=null;
            maxUseTime=String.valueOf(calculateTools.getMaxUseTime(equipmentVariety));
            maxRatedLiftWeight=String.valueOf(calculateTools.getMaxRatedLiftWeight(equipmentVariety));
            maxSpan=String.valueOf(calculateTools.getMaxSpan(equipmentVariety));
            maxRange=String.valueOf(calculateTools.getMaxRange(equipmentVariety));
            maxLiftHeight=String.valueOf(calculateTools.getMaxLiftHeight(equipmentVariety));
            maxLiftSpeed=String.valueOf(calculateTools.getMaxLiftSpeed(equipmentVariety));
            maxRunSpeed=String.valueOf(calculateTools.getMaxRunSpeed(equipmentVariety));
            maxCartSpeed=String.valueOf(calculateTools.getMaxCartSpeed(equipmentVariety));
            maxCarSpeed=String.valueOf(calculateTools.getMaxCarSpeed(equipmentVariety));
            maxLiftMoment=String.valueOf(calculateTools.getMaxLiftMoment(equipmentVariety));
            if(i<craneTypeIdList.size()-1){
                documentJson+="maxUseTime:'"+maxUseTime+"',maxRatedLiftWeight:'"+maxRatedLiftWeight+"',maxSpan:'"+maxSpan+"',maxRange:'"+maxRange+"',maxLiftHeight:'"+maxLiftHeight+"',maxLiftSpeed:'"+maxLiftSpeed+"',maxRunSpeed:'"+maxRunSpeed+"',maxCartSpeed:'"+maxCartSpeed+"',maxCarSpeed:'"+maxCarSpeed+"',maxLiftMoment:'"+maxLiftMoment+"'},";
            }
            if(i==craneTypeIdList.size()-1){
                documentJson+="maxUseTime:'"+maxUseTime+"',maxRatedLiftWeight:'"+maxRatedLiftWeight+"',maxSpan:'"+maxSpan+"',maxRange:'"+maxRange+"',maxLiftHeight:'"+maxLiftHeight+"',maxLiftSpeed:'"+maxLiftSpeed+"',maxRunSpeed:'"+maxRunSpeed+"',maxCartSpeed:'"+maxCartSpeed+"',maxCarSpeed:'"+maxCarSpeed+"',maxLiftMoment:'"+maxLiftMoment+"'}";
            }
            i++;
        }
        return documentJson+"]}";
    }
    public void insertToCraneInspectReportMaxValueCollection(){
        MongoConnector mongo=new MongoConnector("craneInspectReportDB","craneInspectReportMaxValue");
        mongo.insertDocument(getCraneInspectReportMaxValue());
        //mongo.insertDocument("{maxValue:[{typeId:'1',maxUseTime:'1400209815832',maxRatedLiftWeight:'25.0',maxSpan:'null',maxLiftHeight:'16.0',maxLiftSpeed:'1.0',maxRunSpeed:'0.43',maxCartSpeed:'null',maxCarSpeed:'null',maxLiftMoment:'null'},{typeId:'2',maxUseTime:'1400209816604',maxRatedLiftWeight:'null',maxSpan:'null',maxLiftHeight:'18.1',maxLiftSpeed:'0.12',maxRunSpeed:'null',maxCartSpeed:'0.14',maxCarSpeed:'0.11',maxLiftMoment:'null'},{typeId:'3',maxUseTime:'1400209818270',maxRatedLiftWeight:'27.1',maxSpan:'null',maxLiftHeight:'null',maxLiftSpeed:'null',maxRunSpeed:'2.0',maxCartSpeed:'null',maxCarSpeed:'null',maxLiftMoment:'null'},{typeId:'4',maxUseTime:'1400209819341',maxRatedLiftWeight:'32.0',maxSpan:'28.5',maxLiftHeight:'20.0',maxLiftSpeed:'0.29',maxRunSpeed:'null',maxCartSpeed:'1.9',maxCarSpeed:'0.72',maxLiftMoment:'null'}]}");
    }
    public static void main(String[] args){
        /*CraneInspectReportTest craneInspectReportTest=new CraneInspectReportTest();
      *//*  System.out.print(craneInspectReportTest.getDocumentJson("E://门座式起重机.xls"));*//*
        craneInspectReportTest.parseExcel("E://门座式起重机.xls");*/
      /*  String s="Role_User"+"v";
        String[] str=s.split(";");
        for(int i=0;i<str.length;i++){
            System.out.print(str[i]);
        }*/
       /* CraneInspectReportTest test=new CraneInspectReportTest();
        //test.insertToCraneInspectReportMaxValueCollection();
        System.out.println(test.getCraneInspectReportMaxValue());*/
       /* MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","craneInspectReportMaxValue");
      /*  System.out.println(mongoConnector.getMaxValueByCraneType("1").get("maxRatedLiftWeight"));*//*
        dbObjectList=new MongoConnector("craneInspectReportDB","craneInspectReportCollection").getDbArrayListFromMongo();
        for(List<DBObject> d:dbObjectList){
            for(DBObject dd:d){
               System.out.println(dd);
            }
        }*/
    }
}
