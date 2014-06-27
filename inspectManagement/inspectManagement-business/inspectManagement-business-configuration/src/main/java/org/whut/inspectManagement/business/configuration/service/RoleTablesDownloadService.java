package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
import org.whut.inspectManagement.business.deptAndEmployee.entity.SubEmployeeRole;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-30
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
public class RoleTablesDownloadService {
    public String roleTablesDocConstruction(List<SubEmployeeRole> list){
        String result="";
        String roleTemp= "";
        Document doc = DocumentHelper.createDocument();
        Element RolesTable = doc.addElement(ConfigureParameter.RolesTable);
        Iterator<SubEmployeeRole> iterator = list.iterator();
        while(iterator.hasNext()){
            SubEmployeeRole ser = iterator.next();
            Element role = RolesTable.addElement(ConfigureParameter.Role);
            role.addAttribute(ConfigureParameter.name,ser.getName());
            role.addAttribute(ConfigureParameter.roleNum, String.valueOf(ser.getId()));
            String[] tables = ser.getInspectTable().split(";");
            for(String s:tables){
                role.addElement(ConfigureParameter.TableItem).addAttribute(ConfigureParameter.name,s);
            }
        }
        result=new XmlFormat().getXmlStringByFormat(doc);
        return result;
    }
}
