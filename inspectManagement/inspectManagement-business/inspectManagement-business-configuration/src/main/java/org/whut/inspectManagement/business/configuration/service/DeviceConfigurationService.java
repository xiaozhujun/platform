package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.whut.inspectManagement.business.device.entity.DeviceTypeTag;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-5-29
 * Time: 下午6:28
 * To change this template use File | Settings | File Templates.
 */
public class DeviceConfigurationService {

    public String configurationConstruction(List<DeviceTypeTag> list){
        String result="";
        Iterator<DeviceTypeTag> iterator = list.iterator();
        Document doc = DocumentHelper.createDocument();
        Element tags = doc.addElement("tags");
        while(iterator.hasNext()){
            DeviceTypeTag dtt = iterator.next();
            Element tag = tags.addElement("tag");
            tag.addElement("cardType").addText("2");
            tag.addElement("deviceType").addText(dtt.getDeviceType());
            tag.addElement("deviceTypeNum").addText(dtt.getDeviceTypeNumber());
            tag.addElement("deviceNum").addText(dtt.getDeviceNumber());
            tag.addElement("tagArea").addText(dtt.getTagName());
            tag.addElement("tagAreaNum").addText(String.valueOf(dtt.getTagId()));
            tag.addElement("number").addText(dtt.getTagNumber());
        }
        try{
            OutputFormat outputFormat =OutputFormat.createPrettyPrint();
            String encoding = "UTF-8";
            outputFormat.setEncoding(encoding);
            outputFormat.setNewlines(true);
            OutputStream outputStream = new ByteArrayOutputStream();
            XMLWriter xmlWriter = new XMLWriter(outputStream,outputFormat);
            xmlWriter.write(doc);
            xmlWriter.close();
            result=outputStream.toString();
        }
        catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }
}

