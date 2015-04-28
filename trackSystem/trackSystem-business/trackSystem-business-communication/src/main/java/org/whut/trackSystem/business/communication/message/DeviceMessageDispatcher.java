package org.whut.trackSystem.business.communication.message;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.fundamental.activemq.api.PooledMessageDispatcher;
import org.whut.platform.fundamental.activemq.api.PooledMessageProducer;
import org.whut.platform.fundamental.logger.PlatformLogger;
import org.whut.platform.fundamental.redis.connector.RedisConnector;
import org.whut.platform.fundamental.util.json.JsonMapper;
import org.whut.trackSystem.business.device.entity.Device;
import org.whut.trackSystem.business.device.service.DeviceService;
import org.whut.trackSystem.business.trackRecord.entity.TrackRecord;
import org.whut.trackSystem.business.trackRecord.service.TrackRecordService;

import javax.jms.MessageNotWriteableException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: yangyang
 * Date: 14-11-28
 * Time: 上午11:56
 * To change this template use File | Settings | File Templates.
 */
public class DeviceMessageDispatcher implements PooledMessageDispatcher {
    public static final PlatformLogger logger = PlatformLogger.getLogger(DeviceMessageDispatcher.class);
    private static final String destination = Constants.DEVICE_QUEUE_DESTINATION;
    @Autowired
    private PooledMessageProducer pooledMessageProducer;
    private RedisConnector redisConnector = new RedisConnector();
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private TrackRecordService trackRecordService;
    private boolean isFirst = true;

    private Map<String,Date> startTimeMap = new ConcurrentHashMap<String, Date>();
    @Override
    public void dispatchMessage(String messageBody) {
        if (messageBody != null) {
            logger.info("dispatch: " + messageBody);
            try {
                ActiveMQTextMessage message = new ActiveMQTextMessage();
                message.setText(messageBody);
                pooledMessageProducer.sendQueue(destination,message);

                if (isFirst) {
                    logger.info("初始化本次记录");
                    Thread addTrackRecordThread = new Thread(new HandleTrackRecord(isFirst,messageBody,1,deviceService));
                    addTrackRecordThread.start();
                    isFirst = false;
                }
            } catch (MessageNotWriteableException e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Override
    public void exceptionProcess() {
        logger.info("DeviceMessageDispatcher --> exceptionProcess");
        HandleTrackRecord handleTrackRecord = new HandleTrackRecord(isFirst,"",2,deviceService);
        Thread addTrackRecordThread2 = new Thread(handleTrackRecord);
        addTrackRecordThread2.start();
    }

    public class HandleTrackRecord implements Runnable{
        private boolean isFlag;
        private String messageBody;
        private int type;
        private DeviceService deviceService;

        public HandleTrackRecord(boolean isFlag,String messageBody,int type,DeviceService deviceService) {
            this.isFlag = isFlag;
            this.messageBody = messageBody;
            this.type = type;
            this.deviceService = deviceService;
        }

        private synchronized void addTrackRecord(String messageBody) throws JSONException {
            JSONObject object = new JSONObject(messageBody);
            String jsonStr = object.get("devices").toString();
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i=0;i<jsonArray.length();i++) {
                TrackRecord trackRecord = new TrackRecord();
                logger.info("jsonArray.get(" + i + "):" + jsonArray.get(i).toString());
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                Long id = deviceService.getIdByNumber(jsonObject.get("deviceNum").toString(),Long.parseLong(redisConnector.get("appId")));
                trackRecord.setStartTime(new Date());
                startTimeMap.put(jsonObject.get("deviceNum").toString(),trackRecord.getStartTime());
                logger.info("-------------------------" + startTimeMap.toString());
                trackRecord.setDeviceId(id);
                trackRecord.setAppId(Long.parseLong(redisConnector.get("appId")));
                trackRecordService.add(trackRecord);
            }
        }

        private synchronized void updateTrackRecord() throws JSONException {
            List<Device> list = deviceService.getListByAppId(Long.parseLong(redisConnector.get("appId")));
            for (int i=0;i<list.size();i++) {
                TrackRecord trackRecord = new TrackRecord();
                String messageBody = redisConnector.get("device:{"+list.get(i).getNumber()+"}:jsonString");
                if (messageBody != null) {
                    logger.info("????????????????" + messageBody);
                    JSONObject object = new JSONObject(messageBody);
                    String jsonStr = object.get("devices").toString();
                    int startIndex = jsonStr.indexOf("[");
                    int endIndex = jsonStr.indexOf("]");
                    String jsonString = jsonStr.substring(startIndex+1,endIndex);
                    Device curDevice = JsonMapper.buildNonDefaultMapper().fromJson(jsonString,Device.class);
                    curDevice.setNumber(list.get(i).getNumber());
                    Long id = deviceService.getIdByNumber(curDevice.getNumber(),Long.parseLong(redisConnector.get("appId")));
                    curDevice.setId(id);
                    deviceService.update(curDevice);
                    trackRecord.setEndTime(new Date());
                    trackRecord.setDeviceId(id);
                    Date startTime = startTimeMap.get(curDevice.getNumber());
                    trackRecord.setId(trackRecordService.getIdByDeviceNumAndStartTime(list.get(i).getNumber(),
                            startTime, Long.parseLong(redisConnector.get("appId"))));
                    trackRecord.setAppId(Long.parseLong(redisConnector.get("appId")));
                    trackRecordService.update(trackRecord);
                }
            }
        }

        @Override
        public void run() {
            if (type == 1) {
                try {
                    if (isFlag) {
                        addTrackRecord(messageBody);
                    } else {
                        Thread.sleep(2000);
                    }
                } catch (JSONException e) {
                    logger.error(e.getMessage());
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            } else if (!isFlag && type == 2){
                try {
                    updateTrackRecord();
                } catch (JSONException e) {
                    logger.error(e.getMessage());
                }
            } else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
