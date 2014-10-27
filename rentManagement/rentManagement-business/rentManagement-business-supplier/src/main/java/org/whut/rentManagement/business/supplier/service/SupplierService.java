package org.whut.rentManagement.business.supplier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.supplier.entity.Supplier;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoshou
 * Date: 14-10-18
 * Time: 下午8:13
 * To change this template use File | Settings | File Templates.
 */
public class SupplierService {
    @Autowired
    private org.whut.rentManagement.business.supplier.mapper.SupplierMapper supplierMapper;

    public void add(Supplier supplier)
    {
        supplierMapper.add(supplier);
    }
    public void update(Supplier supplier){
        supplierMapper.update(supplier);
    }
    public void delete(Supplier supplier){
        supplierMapper.delete(supplier );
    }
    public List<Supplier> list(long appId,String name,String description,String address,String linkman,String telephone,String email,
                               String qq,String createTime){
        return supplierMapper.findByCondition(appId,name,description,address,linkman,telephone,email,qq,createTime);
    }
    public long getIdByName(String name,long appId){
        return supplierMapper.getIdByName(name, appId);
    }
    public long getIdByTelephone(String telephone){
        return supplierMapper.getIdByTelephone(telephone);
    }
    public List<Supplier> getByNameAndLinkman(String name,String linkman,long appId){
        return  supplierMapper.getByNameAndLinkman(name,linkman,appId);
    }

}
