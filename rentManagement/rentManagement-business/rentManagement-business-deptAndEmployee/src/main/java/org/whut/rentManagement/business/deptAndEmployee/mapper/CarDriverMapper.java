package org.whut.rentManagement.business.deptAndEmployee.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.deptAndEmployee.entity.CarDriver;
import java.util.List;
public interface CarDriverMapper extends AbstractMapper<CarDriver> {
    public long getIdByCarNumber(@Param("carNumber") String carNumber, @Param("appId") long appId);
    public String getCarNumberById(long id);
    public List<CarDriver> list(@Param("appId") long appId);
    public List<CarDriver> getByNameAndCar_Number(@Param("name") String name, @Param("carNumber") String carNumber, @Param("appId") long appId);
}
