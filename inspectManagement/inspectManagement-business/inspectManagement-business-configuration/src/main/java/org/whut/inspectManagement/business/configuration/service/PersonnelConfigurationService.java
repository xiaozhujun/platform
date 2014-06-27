package org.whut.inspectManagement.business.configuration.service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.whut.inspectManagement.business.configuration.entity.ConfigureParameter;
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
        Document doc = DocumentHelper.createDocument();
        Element employees = doc.addElement(ConfigureParameter.employers);
        for(int i=0;i<list.size();i++){
            EmployeeEmployeeRole eer =list.get(i);
            Element employee = employees.addElement(ConfigureParameter.employer);
            employee.addElement(ConfigureParameter.cardType).addText("1");
            employee.addElement(ConfigureParameter.role).addText(eer.getEmployeeRoleName());
            System.out.println(eer.getEmployeeRoleName()+"test");
            employee.addElement(ConfigureParameter.roleNum).addText(String.valueOf(eer.getEmployeeRoleId()));
            employee.addElement(ConfigureParameter.name).addText(eer.getEmployeeName());
            employee.addElement(ConfigureParameter.number).addText(String.valueOf(eer.getEmployeeId()));
        }
        result=new XmlFormat().getXmlStringByFormat(doc);
        return result;
    }
}
