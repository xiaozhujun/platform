package org.whut.platform.business.craneinspectreport.test;

import org.whut.platform.fundamental.jxl.model.ExcelMap;
import org.whut.platform.fundamental.jxl.utils.JxlExportImportUtils;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-18
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
public class CraneInspectReportTest {
    private JxlExportImportUtils jxlExportImportUtils;
    private ExcelMap excelMap=new ExcelMap();

    public String getDocumentJson(String path){
        File f=new File(path);
        excelMap=jxlExportImportUtils.analysisExcel(f);
        String documentJson="{craneinspectreports:[";
        for(int i=0;i<excelMap.getContents().size()-1;i++){
           String documentJson1="{";
            for(int j=0;j<excelMap.getContents().get(i).size()-1;j++){
                documentJson1+=excelMap.getHeads().get(j)+":"+excelMap.getContents().get(i).get(j)+",";
                 if(j+1==excelMap.getContents().get(i).size()-1){
                     documentJson1+=excelMap.getHeads().get(j+1)+":"+excelMap.getContents().get(i).get(j+1);
                 }
            }
            documentJson+=documentJson1+"},";
            if(i+1==excelMap.getContents().size()-1){
                documentJson+=documentJson1+"}";
            }
        }
        documentJson+="]}";
        return documentJson;
    }
    public void parseExcel(String path){
           File file=new File(path);
           excelMap=jxlExportImportUtils.analysisExcel(file);
           for(int i=0;i<excelMap.getContents().size();i++){
               System.out.print(excelMap.getContents().get(i).get(0)+"test");
               for(int j=0;j<excelMap.getContents().get(i).size();j++){
                   System.out.print(excelMap.getContents().get(i).get(j));
               }
           }
    }
    public static void main(String[] args){
        CraneInspectReportTest craneInspectReportTest=new CraneInspectReportTest();
      /*  System.out.print(craneInspectReportTest.getDocumentJson("E://门座式起重机.xls"));*/
        craneInspectReportTest.parseExcel("E://门座式起重机.xls");
    }
}
