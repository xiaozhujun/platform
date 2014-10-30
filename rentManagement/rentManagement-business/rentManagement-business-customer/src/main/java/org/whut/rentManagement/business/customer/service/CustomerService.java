package org.whut.rentManagement.business.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.customer.entity.Customer;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiaoshou
 * Date: 14-10-10
 * Time: 下午5:09
 * To change this template use File | Settings | File Templates.
 */
public class CustomerService {
    @Autowired
    private org.whut.rentManagement.business.customer.mapper.CustomerMapper customerMapper;

    public void add(Customer customer)
    {
        customerMapper.add(customer);
    }
    public void update(Customer customer){
        customerMapper.update(customer);
    }
    public void delete(Customer customer){
        customerMapper.delete(customer );
    }
    public List<Customer> list(long appId,String name,String description,String address,String linkman,String telephone,String email,
                               String qq,String bank,String bankAccount,String createTime){
        return customerMapper.findByCondition(appId,name,description,address,linkman,telephone,email,qq,bank,bankAccount,createTime);
    }
    public long getIdByName(String name,long appId){
        return customerMapper.getIdByName(name, appId);
    }
    public String getNameById(long id,long appId){
        return customerMapper.getNameById(id,appId);
    }
    public long getIdByTelephone(String telephone){
        return customerMapper.getIdByTelephone(telephone);
    }
    public List<Customer> getByNameAndLinkman(String name,String linkman,long appId){
        return  customerMapper.getByNameAndLinkman(name,linkman,appId);
    }

}
