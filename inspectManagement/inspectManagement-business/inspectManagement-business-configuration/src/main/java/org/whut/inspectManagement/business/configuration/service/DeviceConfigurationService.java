package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
import org.whut.inspectManagement.business.device.entity.DeviceTypeTag;

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
        Element tags = doc.addElement(ConfigureParameter.tags);
        while(iterator.hasNext()){
            DeviceTypeTag dtt = iterator.next();
            Element tag = tags.addElement(ConfigureParameter.tag);
            tag.addElement(ConfigureParameter.cardType).addText("2");
            tag.addElement(ConfigureParameter.deviceType).addText(dtt.getDeviceType());
            tag.addElement(ConfigureParameter.deviceTypeNum).addText(dtt.getDeviceTypeNumber());
            tag.addElement(ConfigureParameter.deviceNum).addText(dtt.getDeviceNumber());
            tag.addElement(ConfigureParameter.tagArea).addText(dtt.getTagAreaName());
            tag.addElement(ConfigureParameter.tagAreaNum).addText(String.valueOf(dtt.getTagAreaId()));
            tag.addElement(ConfigureParameter.tagNumber).addText(String.valueOf(dtt.getTagId()));
        }
        result=new XmlFormat().getXmlStringByFormat(doc);
        return result;
    }
}

