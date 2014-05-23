package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.configuration.entity.InspectTableItem;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
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
    public String docConstruction(long tableId){
       String name = inspectTableMapper.getNameById(tableId);
       List<InspectItem> itemList = inspectItemMapper.getInspectItemByInspectTableId(tableId);
       List<InspectTableItem> inspectTableItemList = new ArrayList<InspectTableItem>();
       Iterator iterator = itemList.iterator();
       while(iterator.hasNext()){
           InspectItem  it = (InspectItem)iterator.next();
           InspectTableItem inspectTableItem = new InspectTableItem();
           inspectTableItem.setInspectType(name);
           inspectTableItem.setDeviceType("");
           inspectTableItem.setArea("");
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
       }
       String result=null;
       InspectTableItem i = new InspectTableItem();
       i.setName("1");
       i.setId(1);
       i.setDescription("no");
       i.setInspectType("1");
        i.setArea("1");
       i.setDeviceType("门机");
       i.setInput("true");
       List<InspectTableItem> list = new ArrayList<InspectTableItem>();
       list.add(i);
       String deviceType = "";
       Document doc = DocumentHelper.createDocument();
       Element table = doc.addElement("check").addAttribute("inspecttype",name);
       table.addAttribute("inspecttime","");
       table.addAttribute("worker","");
       table.addAttribute("workernumber","");
       table.addAttribute("devicenumber","");
       Element dt  = table.addElement("deviceType").addAttribute("name","门机");
       String location = "";
       Iterator it = list.iterator();
       System.out.println(">>>>>>>>>>>>>>1");
       Element lc = null;
       System.out.println(">>>>>>>>>>>>>>2");
       while(it.hasNext()){
           InspectTableItem iti = (InspectTableItem)it.next();
           String loc = iti.getArea();
           if(location!=loc){
               location = loc;
               lc = dt.addElement("location").addAttribute("name",location);
            }
       System.out.println(">>>>>>>>>>>>>>3");
       Element item = lc.addElement("field");
       item.addAttribute("name",iti.getName());
       item.addAttribute("id", String.valueOf(iti.getId()));
       item.addAttribute("isInput",iti.getInput());
       item.addAttribute("util","");
       System.out.println(">>>>>>>>>>>>>>4");
       if(iti.getInput().equals("false")){
            System.out.println(">>>>>>>>>>>>>>5");
            Iterator it1 = iti.getValues().iterator();
           System.out.println(">>>>>>>>>>>>>>6");
            while(it1.hasNext()){
                System.out.println(">>>>>>>>>>>>>>7");
                String value = (String) it1.next();
                System.out.println(">>>>>>>>>>>>>>"+value);
                item.addElement("value").addAttribute("name",value);
            }
        }
       System.out.println(">>>>>>>>>>>>>>5");
    }
    try{
        OutputFormat format = OutputFormat.createPrettyPrint();
        String encoding = "utf-8";
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
}
