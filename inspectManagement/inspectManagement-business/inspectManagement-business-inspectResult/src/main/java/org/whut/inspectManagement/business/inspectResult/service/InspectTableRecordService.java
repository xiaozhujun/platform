package org.whut.inspectManagement.business.inspectResult.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.device.mapper.DeviceMapper;
import org.whut.inspectManagement.business.device.mapper.InspectTagMapper;
import org.whut.inspectManagement.business.inspectResult.entity.InspectItemRecord;
import org.whut.inspectManagement.business.inspectResult.entity.InspectTableRecord;
import org.whut.inspectManagement.business.inspectResult.mapper.InspectItemRecordMapper;
import org.whut.inspectManagement.business.inspectResult.mapper.InspectTableRecordMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectChoiceMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectTableMapper;
import org.whut.inspectManagement.business.inspectTable.service.InspectTableService;
import org.whut.platform.business.user.security.UserContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public int DomReadXml(Document document) {
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
        Date createTime=null;
        long inspectTableId;
        long inspectTagId = 0;
        long deviceId = 0;

        InspectTableRecord inspectTableRecord =new InspectTableRecord();
        Element root = document.getRootElement();
        if(root.getName()!="check"){
            flag = 2;
            return flag;
        }
        else{
            tname = root.attribute("inspecttype").getValue();
            t = root.attribute("inspecttime").getValue();
            worknum=root.attribute("workernumber").getValue();
            dnum=root.attribute("devicenumber").getValue();
            if(tname.equals("")||t.equals("")||worknum.equals("")||dnum.equals("")){
                flag = 2;
                return  flag;
            }
            inspectTableId=inspectTableMapper.getIdByNameAndAppId(tname,appId);

            deviceId = deviceMapper.getIdByNumber(dnum,appId);
            Element e1 = root.element("devicetype");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            try{
                createTime = sdf.parse(t);
                System.out.println(createTime);
            }catch (ParseException exception){
                exception.printStackTrace();
            }
            long userId;//解析数据插入到InspectTableRecord

            userId=Long.parseLong(worknum);
            inspectTableRecord.setUseId(userId);
            inspectTableRecord.setInspectTableId(inspectTableId);
            inspectTableRecord.setCreateTime(createTime);
            inspectTableRecord.setExceptionCount(exceptionCount);
            inspectTableRecord.setInspectTableId(inspectTableId);
            inspectTableRecord.setAppId(appId);
            long checkedTableId = 0;
            try{
                checkedTableId = inspectTableRecordMapper.getInspectTableId(t,inspectTableId,appId);}
            catch (Exception e){
                e.printStackTrace();
            }
            if(checkedTableId>0){
                System.out.println("点检结果已存在!");
                flag  =1 ;
                return flag;
            }
            else {
                inspectTableRecordMapper.add(inspectTableRecord);
            }
            long inspectTableRecordId =inspectTableRecordMapper.getInspectTableId(t,inspectTableId,appId);
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
                        System.out.println(">>>>>>>>>>>>>>>>>>"+inspectChoiceValue);
                        if(!(inspectChoiceValue.equals("正常"))){
                            exceptionCount++;
                        }
                        long inspectChoiceId=inspectChoiceMapper.getIdByChoiceValueAndAppId(inspectChoiceValue,appId);
                        inspectItemRecord.setInspectTableId(inspectTableId);
                        inspectItemRecord.setInspectTagId(inspectTagId);
                        inspectItemRecord.setInspectItemId(itemId1);
                        inspectItemRecord.setInspectChoiceId(inspectChoiceId);
                        inspectItemRecord.setInspectChoiceValue(inspectChoiceValue);
                        inspectItemRecord.setInspectTableRecordId(inspectTableRecordId);
                        inspectItemRecord.setUserId(userId);
                        inspectItemRecord.setDeviceId(deviceId);
                        inspectItemRecord.setAppId(appId);
                        inspectItemRecordMapper.add(inspectItemRecord);
                        System.out.println(tname + area + createTime+ item + inspectChoiceValue + worknum  +tableRecid + dnum);
                    }
                }
            }

            inspectTableRecordMapper.updateTableRecord(exceptionCount,inspectTableId,appId);

        }
        return flag;
    }

}

