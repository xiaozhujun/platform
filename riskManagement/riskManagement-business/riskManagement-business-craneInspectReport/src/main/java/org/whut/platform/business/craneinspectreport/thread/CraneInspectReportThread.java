package org.whut.platform.business.craneinspectreport.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.craneinspectreport.service.CraneInspectReportService;
import org.whut.platform.fundamental.util.json.JsonResultUtils;

/**
 * Created with IntelliJ IDEA.
 * User: hadoop
 * Date: 14-12-18
 * Time: 下午3:05
 * To change this template use File | Settings | File Templates.
 */
public class CraneInspectReportThread implements Runnable{
    @Autowired
    private CraneInspectReportService craneInspectReportService;

    private String reportId;

    public CraneInspectReportThread(String reportId){
        this.reportId=reportId;
    }
    public void run(){

    }
    public void calculateRiskThread(){
        //先计算最大值,计算完毕后将status更新为1
        if(calculateMaxValue().equals("success")){

        }
        //再计算勾起来的文档,计算完毕后将status更新为2


        //计算省市区的风险值,然后将status更新为3

        //复制数据,将status更新为4
    }
    public String calculateMaxValue(){
        String r=craneInspectReportService.insertToCraneInspectReportMaxValueCollection();
        if(r.equals("0")){

        }else if(r.equals("1")){
            return "success";
        }
        return null;
    }
}
