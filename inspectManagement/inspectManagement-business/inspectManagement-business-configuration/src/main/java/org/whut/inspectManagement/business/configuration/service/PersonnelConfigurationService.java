package org.whut.inspectManagement.business.configuration.service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.whut.inspectManagement.business.deptAndEmployee.entity.EmployeeEmployeeRole;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-18
 * Time: 下午12:08
 * To change this template use File | Settings | File Templates.
 */
public class PersonnelConfigurationService {
    public String configurationConstruction(List<EmployeeEmployeeRole> list){
        String result="";
        Iterator<EmployeeEmployeeRole> iterator = list.iterator();
        Document doc = DocumentHelper.createDocument();
        Element employees = doc.addElement("employees");
        while(iterator.hasNext()){
            EmployeeEmployeeRole eer = iterator.next();
            Element employee = employees.addElement("employee");
            employee.addElement("cardType").addText("1");
            employee.addElement("role").addText(eer.getEmployeeRoleName());
            employee.addElement("roleNum").addText(String.valueOf(eer.getEmployeeRoleId()));
            employee.addElement("name").addText(eer.getEmployeeName());
            employee.addElement("number").addText(String.valueOf(eer.getEmployeeId()));
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
