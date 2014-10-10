package org.whut.monitor.business.communication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.monitor.business.communication.bean.CollectorStatus;
import org.whut.monitor.business.monitor.service.CollectorService;
import org.whut.monitor.business.monitor.service.SensorService;
import org.whut.platform.business.user.security.UserContext;
import org.whut.platform.fundamental.redis.connector.RedisConnector;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-9-19
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public class CollectorStatusService {
    @Autowired
    private SensorService sensorService;
    @Autowired
    private CollectorService collectorService;
    private RedisConnector redisConnector = new RedisConnector();

    private Map<String,List<String>> map = new HashMap<String, List<String>>();
    public void add(String number) {
        String collectorNumber = sensorService.getCNumBySNum(number);
        System.out.println("传感器编号：" + collectorNumber);
        String collectorName = collectorService.getCollectNameById(collectorService.getIdByNumberAndAppId(collectorNumber, 1));
        System.out.println("传感器名字：" + collectorName);
        this.map = CollectorStatus.getMap();
        if (this.map.get(collectorName) == null) {
            this.map.put(collectorName,new ArrayList<String>());
        }
        for (int i=0; i<this.map.get(collectorName).size(); i++) {
            if (this.map.get(collectorName).get(i).equals(number)) {
                System.out.println("该传感器已存在于列表中 ！");
                return;
            }
        }
        this.map.get(collectorName).add(number);
        CollectorStatus.setMap(this.map);
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyy " + CollectorStatus.getMap());
    }

    public boolean delete(String number) {
        Iterator iterator = this.map.entrySet().iterator();
//        List<String> list = new ArrayList<String>();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = entry.getKey().toString();
            System.out.println("collectorNumber :" + key);
            List<String> val = (List<String>)entry.getValue();
            System.out.println("collectorNumber :" + val);
            if (val.contains(number)) {
                val.remove(number);
            }
            else {
                System.out.println(key + "不包含传感器" + number);
            }
            System.out.println("ffffffffffffffff :" + this.map.get(key));
            if (this.map.get(key).size() == 0) {
                return true;
            }
        }
        return false;
    }
}
