package org.whut.rentManagement.business.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.whut.rentManagement.business.stock.entity.Stock_in_sheet;
import org.whut.rentManagement.business.stock.mapper.Stock_in_sheetMapper;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Jiaju
 * Date: 14-10-14
 * Time: 下午4:25
 * To change this template use File | Settings | File Templates.
 */
public class Stock_in_sheetService {
    @Autowired
    private Stock_in_sheetMapper stockInSheetMapper;


    public void add(Stock_in_sheet stockInSheet){
        stockInSheetMapper.add(stockInSheet);
    }

    public void update(Stock_in_sheet stockInSheet){
        stockInSheetMapper.update(stockInSheet);
    }

    public void delete(Stock_in_sheet stockInSheet){
        stockInSheetMapper.delete(stockInSheet);
    }

    public List<Stock_in_sheet> getListByAppId(long appId){
        return  stockInSheetMapper.getListByAppId(appId);
    }


    public List<Map<String,String>> getInStockList(Map<String,Object> condition){
        return stockInSheetMapper.getInStockList(condition);
    }
}
