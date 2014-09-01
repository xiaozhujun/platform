package org.whut.inspectManagement.business.inspectResult.service;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.inspectManagement.business.inspectResult.entity.SearchBean;
import org.whut.inspectManagement.business.inspectResult.mapper.InspectItemRecordMapper;
import org.whut.platform.fundamental.android.cas.CasClient;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: John
 * Date: 14-5-13
 * Time: 下午2:08
 * To change this template use File | Settings | File Templates.
 */
public class InspectItemRecordService {

    @Autowired
    private InspectItemRecordMapper inspectItemRecordMapper;

    public List<SearchBean> findByCondition(Map<String,Object> map){
        return inspectItemRecordMapper.findByCondition(map);
    }

    public void updateMaintainIdAndSuggest(Map<String,Object>map){
        inspectItemRecordMapper.updateMaintainIdAndSuggest(map);
    }

    public long getTableRecordIdByItemRecordId(long itemRecordId) {
        return inspectItemRecordMapper.getTableRecordIdByItemRecordId(itemRecordId);
    }

    public String sendMessage(String telNumber,String maintainSuggest) throws DocumentException {
        FundamentalConfigProvider fundamentalConfigProvider = new FundamentalConfigProvider();
        HashMap<String,String> map = new HashMap<String, String>();
        initHashMap(map,telNumber,maintainSuggest);
        String casBaseUrl = fundamentalConfigProvider.getProperties().get("sms.init.casBaseUrl").toString();
        CasClient casClient = new CasClient(casBaseUrl);
//        casClient.getHttpClient().getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,"UTF-8");
//        casClient.getHttpClient().getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
        System.out.println("初始化httpClient：" + casClient.getClass());
        String xmlString = casClient.doPost("http://sms.106vip.com/sms.aspx",map);
        Document document = readStringXML(xmlString);
        Element element = document.getRootElement();
        Map<String,String> temMap = getValue(element);
        String returnStatus = temMap.get("returnstatus");
        System.out.println(map);
        return returnStatus;
    }

    //字符串转化为xml文件
    private Document readStringXML(String xml) throws DocumentException {
        Document doc = null;
        doc = DocumentHelper.parseText(xml);
        return doc;
    }
    //取得Root节点
    private Element getRootElement(Document doc) {
//		System.out.println(doc.getRootElement().elementText("returnstatus"));
        return doc.getRootElement();
    }
    //获取属性值
    private Map<String,String> getValue(Element root) {
        Map<String,String> map = new HashMap<String, String>();
        for(Iterator i = root.elementIterator(); i.hasNext();) {
            Element element = (Element)i.next();
            map.put(element.getName(),element.getText());
            System.out.println(element.getName() + "|" + element.getText());
        }
        return map;
    }
    //初始化表单参数
    private void initHashMap(Map<String,String> map,String number,String suggest) {
        FundamentalConfigProvider fundamentalConfigProvider = new FundamentalConfigProvider();
        map.put("userid",fundamentalConfigProvider.getProperties().get("sms.user.id").toString());
        map.put("account",fundamentalConfigProvider.getProperties().get("sms.user.userName").toString());
        map.put("password",fundamentalConfigProvider.getProperties().get("sms.user.password").toString());
        map.put("action",fundamentalConfigProvider.getProperties().get("sms.send.action").toString());
        map.put("checkcontent",fundamentalConfigProvider.getProperties().get("sms.send.checkContent").toString());
        map.put("taskName",fundamentalConfigProvider.getProperties().get("sms.send.taskName").toString());
        map.put("countnumber",fundamentalConfigProvider.getProperties().get("sms.send.countnumber").toString());
        map.put("mobilenumber",fundamentalConfigProvider.getProperties().get("sms.send.mobilenumber").toString());
        map.put("telephonenumber",fundamentalConfigProvider.getProperties().get("sms.send.telephonenumber").toString());
        map.put("mobile",number);
        map.put("content",suggest);
        map.put("sendTime","");
    }
}
