package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
import org.whut.inspectManagement.business.configuration.entity.InspectTableItem;
import org.whut.inspectManagement.business.device.mapper.InspectAreaMapper;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemChoiceMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectTableMapper;
import org.whut.platform.business.user.security.UserContext;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-20
 * Time: 下午6:40
 * To change this template use File | Settings | File Templates.
 */
public class InspectTableDownloadService {
    @Autowired
    private InspectTableMapper inspectTableMapper;

    @Autowired
    private InspectItemMapper inspectItemMapper;

    @Autowired
    private InspectItemChoiceMapper inspectItemChoiceMapper;

    @Autowired
    private InspectAreaMapper inspectAreaMapper;
    public String docConstruction(long tableId){
       String name = inspectTableMapper.getNameById(tableId);
      long appId = UserContext.currentUserAppId();
       List<InspectItem> itemList = inspectItemMapper.getInspectItemByInspectTableId(tableId,appId);
       List<InspectTableItem> inspectTableItemList = new ArrayList<InspectTableItem>();
       Iterator iterator = itemList.iterator();
        Document doc = DocumentHelper.createDocument();
       while(iterator.hasNext()){
           InspectItem  it = (InspectItem)iterator.next();
           InspectTableItem inspectTableItem = new InspectTableItem();
           inspectTableItem.setInspectType(name);
           Long inspectAreaId = it.getInspectAreaId();
           inspectTableItem.setAreaId(inspectAreaId);
           String deviceType = inspectAreaMapper.getDeviceTypeByAreaId(inspectAreaId);
           inspectTableItem.setDeviceType(deviceType);
           String area = inspectAreaMapper.getAreaById(inspectAreaId);
           inspectTableItem.setArea(area);
           inspectTableItem.setName(it.getName());
           inspectTableItem.setId(it.getId());
           int is = it.getInput();
           if(is==0){
               inspectTableItem.setInput("false");
           }
           else{
               inspectTableItem.setInput("true");
           }
           inspectTableItem.setDescription(it.getDescription());
           Long itemId = it.getId();
           List<String> valueList = inspectItemChoiceMapper.getChoicesByItemId(itemId);
           inspectTableItem.setValues(valueList);
           inspectTableItemList.add(inspectTableItem);
       }

        String result=null;

       if(inspectTableItemList.size()>0){


           String dType = inspectTableItemList.get(0).getDeviceType();

           Element table = doc.addElement(ConfigureParameter.check).addAttribute(ConfigureParameter.inspecttype,name);
           table.addAttribute(ConfigureParameter.inspecttime,"");
           table.addAttribute(ConfigureParameter.worker,"");
           table.addAttribute(ConfigureParameter.workernumber,"");
           table.addAttribute(ConfigureParameter.devicenumber,"");
           Element dt  = table.addElement(ConfigureParameter.devicetype).addAttribute(ConfigureParameter.name,dType);
           String location = "";
           Iterator it = inspectTableItemList.iterator();
           Element lc = null;
           while(it.hasNext()){
               InspectTableItem iti = (InspectTableItem)it.next();
               String loc = iti.getArea();
               if(!location.equals(loc)){
                   location = loc;
                   lc = dt.addElement(ConfigureParameter.location).addAttribute(ConfigureParameter.name,loc);
                   lc.addAttribute(ConfigureParameter.areaId, String.valueOf(iti.getAreaId()));
               }
               Element item = lc.addElement(ConfigureParameter.field);
               item.addAttribute(ConfigureParameter.name,iti.getName());
               item.addAttribute(ConfigureParameter.itemId, String.valueOf(iti.getId()));
               item.addAttribute(ConfigureParameter.isInput,iti.getInput());
               item.addAttribute(ConfigureParameter.util,"");
               item.addAttribute(ConfigureParameter.description,iti.getDescription());
               if(iti.getInput().equals("false")){
                   Iterator it1 = iti.getValues().iterator();
                   while(it1.hasNext()){
                       String value = (String) it1.next();
                       item.addElement(ConfigureParameter.value).addAttribute(ConfigureParameter.name,value);
                   }
               }
           }
       }
       result=new XmlFormat().getXmlStringByFormat(doc);
       return  result;
   }

   public String getTableNameById(long id){
      return inspectTableMapper.getNameById(id);
   }
}
