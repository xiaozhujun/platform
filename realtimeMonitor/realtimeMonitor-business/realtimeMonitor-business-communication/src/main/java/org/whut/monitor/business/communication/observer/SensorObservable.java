package org.whut.monitor.business.communication.observer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: YangRichard
 * Date: 14-10-11
 * Time: 上午9:14
 * To change this template use File | Settings | File Templates.
 */
public class SensorObservable extends Observable {
//    private Map<String,Object> map = new HashMap<String, Object>();
//
//    public Map<String, Object> getMap() {
//        return map;
//    }
//
//    public void setMap(Map<String, Object> map) {
//        this.map = map;
//        setChanged();
//        notifyObservers(this.map);
//    }
    private Object object = new Object();

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
        setChanged();
        notifyObservers(this.object);
    }
}
