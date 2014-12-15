package org.whut.monitor.business.dataTest.service;

import org.whut.monitor.business.dataTest.misc.SimulatorEnum;
import org.whut.monitor.business.dataTest.misc.SimulatorThread;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-12-12
 * Time: 上午10:42
 * To change this template use File | Settings | File Templates.
 */
public class SensorSimulatorService {
    /**
     * 对模拟线程进行开启、关闭操作
     * @param simulatorEnum
     */
    public void doSimulator(SimulatorEnum simulatorEnum){
        switch (simulatorEnum){
            case START:
                SimulatorThread.start();
                break;
            case STOP:
                SimulatorThread.stop();
                break;
            default:break;
        }
    }

    /**
     * 设置时间间隔
     * @param interval
     */
    public void setInterval(int interval){
        SimulatorThread.setInterval(interval);
    }

    /**
     * 设置编号前缀
     * @param prefix
     */
    public void setPrefix(String prefix){
        SimulatorThread.setPrefix(prefix);
    }
}
