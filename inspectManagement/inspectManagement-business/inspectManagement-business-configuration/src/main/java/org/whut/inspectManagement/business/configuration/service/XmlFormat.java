package org.whut.inspectManagement.business.configuration.service;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-26
 * Time: 下午7:22
 * To change this template use File | Settings | File Templates.
 */
public class XmlFormat {
    public String getXmlStringByFormat(Document doc){
        String result=null;
        try{
            StringWriter writer=new StringWriter();
            OutputFormat format = OutputFormat.createPrettyPrint();
            String encoding = "UTF-8";
            format.setEncoding(encoding);
            format.setNewlines(true);
            // OutputStream outputStream = new ByteArrayOutputStream();
            XMLWriter xmlWriter = new XMLWriter(writer,format);
            xmlWriter.write(doc);
            writer.close();
            result = writer.toString();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
}
