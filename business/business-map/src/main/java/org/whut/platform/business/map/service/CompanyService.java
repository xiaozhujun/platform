package org.whut.platform.business.map.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.platform.business.map.mapper.CompanyMapper;

/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class CompanyService {
    @Autowired
    private CompanyMapper mapper;

    public Long findIdByName(String name){
        if(name==null||name.trim().equals("")){
            return null;
        }
        return mapper.findIdByName(name);
    }



}
