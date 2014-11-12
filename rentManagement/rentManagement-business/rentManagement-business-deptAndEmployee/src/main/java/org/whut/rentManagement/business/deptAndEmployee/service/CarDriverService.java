package org.whut.rentManagement.business.deptAndEmployee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.deptAndEmployee.entity.CarDriver;

import java.util.List;

public class CarDriverService {
    @Autowired
    private org.whut.rentManagement.business.deptAndEmployee.mapper.CarDriverMapper carDriverMapper;

    public void add( CarDriver car_driver){
        carDriverMapper.add(car_driver);
    }
    public void delete( CarDriver car_driver){
        carDriverMapper.delete(car_driver);
    }
    public void update( CarDriver car_driver){
        carDriverMapper.update(car_driver);
    }
    public List<CarDriver> list(long appId) {
        return carDriverMapper.list(appId);
    }
    public long getIdByCarNumber(String carNumber,long appId){
        return carDriverMapper.getIdByCarNumber(carNumber, appId);
    }
    public String getCarNumberById(long id){
        return carDriverMapper.getCarNumberById(id);
    }
    public List<CarDriver> getByNameAndCar_Number(String name,String carNumber,long appId){
        return  carDriverMapper.getByNameAndCar_Number(name,carNumber,appId);
    }

}
