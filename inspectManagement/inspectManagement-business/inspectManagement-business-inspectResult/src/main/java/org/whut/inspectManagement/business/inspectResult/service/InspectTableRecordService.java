package org.whut.inspectManagement.business.inspectResult.service;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.deptAndEmployee.mapper.EmployeeMapper;
import org.whut.inspectManagement.business.device.mapper.DeviceMapper;
import org.whut.inspectManagement.business.device.mapper.InspectTagMapper;
import org.whut.inspectManagement.business.inspectResult.entity.InspectItemRecord;
import org.whut.inspectManagement.business.inspectResult.entity.InspectTableRecord;
import org.whut.inspectManagement.business.inspectResult.mapper.InspectItemRecordMapper;
import org.whut.inspectManagement.business.inspectResult.mapper.InspectTableRecordMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectChoiceMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectTableMapper;
import org.whut.inspectManagement.business.task.entity.InspectTask;
import org.whut.inspectManagement.business.task.mapper.InspectTaskMapper;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.mongo.connector.MongoConnector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
public class InspectTableRecordService {
    @Autowired
    InspectTableRecordMapper inspectTableRecordMapper;
    @Autowired
    InspectTableMapper inspectTableMapper;
    @Autowired
    InspectItemRecordMapper inspectItemRecordMapper;
    @Autowired
    InspectChoiceMapper inspectChoiceMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    InspectTagMapper inspectTagMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    InspectTaskMapper inspectTaskMapper;

    private MongoConnector mongoConnector=new MongoConnector("craneInspectReportDB","inspectItemRecordCollection");

    public int DomReadXml(Document document) {
        List<InspectItemRecord> exceptionRecordList = new ArrayList<InspectItemRecord>();
        long appId= UserContext.currentUserAppId();
        int flag = 0;
        String tname = null;
        String area = null;
        String areaId = null;
        String item = null;
        String itemId =null;
        String inspectChoiceValue = null;
        String t = null;
        String dnum=null;
        long tableRecid=0;
        String worknum=null;
        int exceptionCount=0;
        Date inspectTime=null;
        long inspectTableId=0;
        long inspectTagId = 0;
        long deviceId = 0;
        long userId;//解析数据插入到InspectTableRecord
        String mongoId;
        String comment=null;
        List<InspectItemRecord> inspectItemRecords=new ArrayList<InspectItemRecord>();
        InspectTableRecord inspectTableRecord =new InspectTableRecord();
        Element root = document.getRootElement();
        if(root.getName()!="check"){
            flag = 2;
            return flag;
        }
        else{
            tname = root.attribute("inspecttype").getValue();
            t = filterDateString(root.attribute("inspecttime").getValue());
            worknum=root.attribute("workernumber").getValue();
            userId=Long.parseLong(worknum);
            dnum=root.attribute("devicenumber").getValue();
            if(tname.equals("")||t.equals("")||worknum.equals("")||dnum.equals("")){
                flag = 2;
                return  flag;
            }
            try{
                inspectTableId=inspectTableMapper.getIdByNameAndAppId(tname,appId);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            if(inspectTableId==0)
            {
                flag = 3;
                return flag;
            }

            deviceId = deviceMapper.getIdByNumber(dnum,appId);
            Element e1 = root.element("devicetype");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            try{
                inspectTime = sdf.parse(t);
                System.out.println(inspectTime);
            }catch (ParseException exception){
                exception.printStackTrace();
            }


            long checkedTableId = 0;
            if(inspectTableRecordMapper.getInspectTableId(t,inspectTableId,appId)!=null){
            try{
                checkedTableId = inspectTableRecordMapper.getInspectTableId(t,inspectTableId,appId);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            }
            if(checkedTableId>0){
                System.out.println("点检结果已存在!");
                flag  =1 ;
                return flag;
            }
            else if(inspectTableRecordMapper.getInspectTableId(t,inspectTableId,appId)==null){

                List<Element> e2 = e1.elements();
                Iterator<Element> it2 = e2.iterator();
                while (it2.hasNext()) {
                    Element e5 = it2.next();
                    System.out.println(e5.getName() + ":"
                            + e5.attribute("name").getValue());
                    area = e5.attribute("name").getValue();
                    areaId = e5.attribute("areaId").getValue();
                    long inspectAreaId = Long.parseLong(areaId);
                    inspectTagId = inspectTagMapper.getIdByDeviceNumAndAreaId(dnum,inspectAreaId,appId);
                    List<Element> elements = e5.elements();
                    Iterator<Element> it = elements.iterator();
                    while (it.hasNext()) {
                        Element e = it.next();
                        item = e.attribute("name").getValue();
                        itemId =e.attribute("itemId").getValue();
                        System.out.println(">>>>>>>>>>>>>>>>>>"+itemId);
                        long itemId1=Long.parseLong(itemId);
                        System.out.println(">>>>>>>>>>>>>>>>>>"+itemId1);
                        List<Element> group = e.elements();
                        Iterator<Element> git = group.iterator();
                        while (git.hasNext()) {
                            InspectItemRecord inspectItemRecord =new InspectItemRecord();
                            Element ge = git.next();
                            inspectChoiceValue = ge.attribute("name").getValue();
                            Attribute a =ge.attribute("comment");
                            if (a!=null)
                            {
                                comment=a.getValue();
                                if(!comment.equals(""))
                                    inspectItemRecord.setNote(comment);
                            }
                            System.out.println(">>>>>>>>>>>>>>>>>>"+inspectChoiceValue);
                            if(!(inspectChoiceValue.equals("正常"))){
                                exceptionCount++;

                                exceptionRecordList.add(inspectItemRecord);
                            }

                            long inspectChoiceId=inspectChoiceMapper.getIdByChoiceValueAndAppId(inspectChoiceValue,appId);
                            inspectItemRecord.setInspectTableId(inspectTableId);
                            inspectItemRecord.setInspectTagId(inspectTagId);
                            inspectItemRecord.setInspectItemId(itemId1);
                            inspectItemRecord.setInspectChoiceId(inspectChoiceId);
                            inspectItemRecord.setInspectChoiceValue(inspectChoiceValue);
                            //inspectItemRecord.setInspectTableRecordId(inspectTableRecordId);
                            inspectItemRecord.setUserId(userId);
                            inspectItemRecord.setDeviceId(deviceId);
                            inspectItemRecord.setAppId(appId);
                            inspectItemRecord.setCreateTime(new Date());
                            inspectItemRecord.setInspectTime(inspectTime);
                            //inspectItemRecordMapper.add(inspectItemRecord);
                            inspectItemRecords.add(inspectItemRecord);
                            System.out.println(tname + area + inspectTime+ item + inspectChoiceValue + worknum  +tableRecid + dnum);

                        }
                    }
                }
                //添加点检记录项到数据库
                mongoId = insertToInspectItemRecordCollection(inspectItemRecords);
                //long inspectTableRecordId =inspectTableRecordMapper.getInspectTableId(t,inspectTableId,appId);

                //添加点检结果记录
                inspectTableRecord.setUseId(userId);
                inspectTableRecord.setInspectTableId(inspectTableId);
                inspectTableRecord.setCreateTime(new Date());
                inspectTableRecord.setInspectTime(inspectTime);
                inspectTableRecord.setExceptionCount(exceptionCount);
                inspectTableRecord.setInspectTableId(inspectTableId);
                inspectTableRecord.setMongoId(mongoId);
                inspectTableRecord.setDeviceId(deviceId);
                inspectTableRecord.setAppId(appId);
                inspectTableRecordMapper.add(inspectTableRecord);

                //根据点检结果更新任务状态
                SimpleDateFormat taskDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                InspectTask inspectTask =  new InspectTask();
                inspectTask.setInspectTableId(inspectTableId);
                inspectTask.setUserId(userId);
                inspectTask.setDeviceId(deviceId);
                inspectTask.setAppId(appId);
                try {
                    Date taskDate = taskDateFormat.parse(taskDateFormat.format(inspectTime));
                    inspectTask.setTaskDate(taskDate);
                } catch (ParseException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                inspectTask.setTimeStart(inspectTime.getHours()+1);
                inspectTask.setStatus(1);
                inspectTask.setInspectTime(inspectTime);
                inspectTask.setInspectTableRecordId(inspectTableRecord.getId());
                inspectTask.setFaultCount(exceptionCount);
                inspectTaskMapper.completeTask(inspectTask);

                for (InspectItemRecord exceptionRecord:exceptionRecordList){
                    exceptionRecord.setInspectTableRecordId(inspectTableRecord.getId());
                    inspectItemRecordMapper.add(exceptionRecord);
                }

                flag=5;
            }
            //inspectTableRecordMapper.updateTableRecord(exceptionCount,inspectTableId,t,appId);
        }

        return flag;
    }
      public String insertToInspectItemRecordCollection(List<InspectItemRecord> inspectItemRecords){
          int len=inspectItemRecords.size();
          String mongoString="{inspectitemrecords:[";
          for(int i=0;i<len;i++){
              String mongoId=inspectItemRecords.get(i).getUserId()+""+inspectItemRecords.get(i).getDeviceId()+""+inspectItemRecords.get(i).getInspectTableId();
              mongoString+="{'inspectTableId':'"+inspectItemRecords.get(i).getInspectTableId()+
                      "','inspectTagId':'"+inspectItemRecords.get(i).getInspectTagId()+"','inspectItemId':'"+inspectItemRecords.get(i).getInspectItemId()+
                      "','inspectChoiceId':'"+inspectItemRecords.get(i).getInspectChoiceId()+"','inspectChoiceValue':'"+inspectItemRecords.get(i).getInspectChoiceValue()+
                      "','inspectTableRecordId':'"+inspectItemRecords.get(i).getInspectTableRecordId()+"','userId':'"+inspectItemRecords.get(i).getUserId()+
                      "','deviceId':'"+inspectItemRecords.get(i).getDeviceId()+"','appId':'"+inspectItemRecords.get(i).getAppId()+"','note':'"+inspectItemRecords.get(i).getNote()+"'},";
            if(i+1==len){
                mongoString+="{'inspectTableId':'"+inspectItemRecords.get(i).getInspectTableId()+
                        "','inspectTagId':'"+inspectItemRecords.get(i).getInspectTagId()+"','inspectItemId':'"+inspectItemRecords.get(i).getInspectItemId()+
                        "','inspectChoiceId':'"+inspectItemRecords.get(i).getInspectChoiceId()+"','inspectChoiceValue':'"+inspectItemRecords.get(i).getInspectChoiceValue()+
                        "','inspectTableRecordId':'"+inspectItemRecords.get(i).getInspectTableRecordId()+"','userId':'"+inspectItemRecords.get(i).getUserId()+
                        "','deviceId':'"+inspectItemRecords.get(i).getDeviceId()+"','appId':'"+inspectItemRecords.get(i).getAppId()+"','note':'"+inspectItemRecords.get(i).getNote()+"'}";
            }
          }
          mongoString+="]}";
          return mongoConnector.insertDocument(mongoString);
      }
      public String filterDateString(String d){
          /*String s=null;
          try{
              SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
              Date dd=sdf.parse(d);
              s=sdf.format(dd);
          }catch (Exception e){
              e.printStackTrace();
          }
          return s;*/
          return d;
      }

    public InspectTableRecord getById(long id){
        InspectTableRecord inspectTableRecord = new InspectTableRecord();
        inspectTableRecord.setId(id);
        return inspectTableRecordMapper.get(inspectTableRecord);
    }
}

