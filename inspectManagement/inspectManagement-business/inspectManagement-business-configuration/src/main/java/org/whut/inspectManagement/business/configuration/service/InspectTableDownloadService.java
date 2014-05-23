package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.configuration.entity.InspectTableItem;
import org.whut.inspectManagement.business.device.mapper.InspectAreaMapper;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItemChoice;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemChoiceMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectItemMapper;
import org.whut.inspectManagement.business.inspectTable.mapper.InspectTableMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
       List<InspectItem> itemList = inspectItemMapper.getInspectItemByInspectTableId(tableId);
       List<InspectTableItem> inspectTableItemList = new ArrayList<InspectTableItem>();
       Iterator iterator = itemList.iterator();
       while(iterator.hasNext()){
           InspectItem  it = (InspectItem)iterator.next();
           InspectTableItem inspectTableItem = new InspectTableItem();
           inspectTableItem.setInspectType(name);
           Long inspectAreaId = it.getInspectAreaId();
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
       String dType = inspectTableItemList.get(0).getDeviceType();
       Document doc = DocumentHelper.createDocument();
       Element table = doc.addElement("check").addAttribute("inspecttype",name);
       table.addAttribute("inspecttime","");
       table.addAttribute("worker","");
       table.addAttribute("workernumber","");
       table.addAttribute("devicenumber","");
       Element dt  = table.addElement("deviceType").addAttribute("name",dType);
       String location = "";
       Iterator it = inspectTableItemList.iterator();
       Element lc = null;
       while(it.hasNext()){
           InspectTableItem iti = (InspectTableItem)it.next();
           String loc = iti.getArea();
           if(location!=loc){
               location = loc;
               lc = dt.addElement("location").addAttribute("name",loc);
            }
       Element item = lc.addElement("field");
       item.addAttribute("name",iti.getName());
       item.addAttribute("id", String.valueOf(iti.getId()));
       item.addAttribute("isInput",iti.getInput());
       item.addAttribute("util","");
       if(iti.getInput().equals("false")){
            Iterator it1 = iti.getValues().iterator();
            while(it1.hasNext()){
                String value = (String) it1.next();
                item.addElement("value").addAttribute("name",value);
            }
        }
    }
    try{
        OutputFormat format = OutputFormat.createPrettyPrint();
        String encoding = "gbk";
        format.setEncoding(encoding);
        format.setNewlines(true);
        OutputStream outputStream = new ByteArrayOutputStream();
        XMLWriter writer = new XMLWriter(outputStream,format);
        writer.write(doc);
        writer.close();
        result = outputStream.toString();
        System.out.println(">>>>>>"+result);
    }
    catch(IOException e){
        e.printStackTrace();
    }
       return  result;
   }

   public String getTableNameById(long id){
      return inspectTableMapper.getNameById(id);
   }
}
