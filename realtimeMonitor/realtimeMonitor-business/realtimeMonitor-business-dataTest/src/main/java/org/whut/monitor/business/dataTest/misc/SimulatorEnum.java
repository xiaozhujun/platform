package org.whut.monitor.business.dataTest.misc;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-12-12
 * Time: 上午10:53
 * To change this template use File | Settings | File Templates.
 */
public enum SimulatorEnum {
    //启动，停止
    START(1),STOP(2);
    private int value;
    private SimulatorEnum(int value){
        this.value=value;
    }

    public int getValue(){
        return this.value;
    }
}
