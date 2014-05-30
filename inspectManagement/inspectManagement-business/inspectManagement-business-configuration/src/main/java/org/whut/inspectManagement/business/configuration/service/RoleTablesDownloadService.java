package org.whut.inspectManagement.business.configuration.service;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

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
    public String roleTablesDocConstruction(JSONArray jsonArray) throws JSONException {
        String result="";
        Document doc = DocumentHelper.createDocument();
        Element RolesTable = doc.addElement("RolesTable");
        for(int i= 0;i<jsonArray.length();i++){
            JSONObject jsonObject = (JSONObject)(jsonArray.get(i));
            Element role = RolesTable.addElement("Role");
            role.addAttribute("name",jsonObject.getString("name"));
            role.addAttribute("roleNum",jsonObject.getString("id"));
            String inspectTable = jsonObject.getString("inspectTable");
            String[] tables  = inspectTable.split(";");
            if(tables.length>0){
                for(int j=0;j<tables.length;j++){
                     role.addElement("TableItem").addAttribute("name",tables[j]);
                }
            }

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
