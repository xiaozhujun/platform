package org.whut.rentManagement.business.device.misc;

/**
 * Created with IntelliJ IDEA.
 * User: xiaozhujun
 * Date: 14-11-25
 * Time: 下午2:39
 * To change this template use File | Settings | File Templates.
 */
public enum OptionType {

    //入库，出库，运输，安装，拆卸
    STOCKIN(1),STOCKOUT(2),TRANSPORT(3),INSTALL(4),REMOVE(5);

    private int value;
    private OptionType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
