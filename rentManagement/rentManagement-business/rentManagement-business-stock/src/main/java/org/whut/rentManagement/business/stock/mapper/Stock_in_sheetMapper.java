package org.whut.rentManagement.business.stock.mapper;

import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
import org.whut.rentManagement.business.stock.entity.Stock_in_sheet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Jiaju
 * Date: 14-10-14
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public interface Stock_in_sheetMapper  extends AbstractMapper<Stock_in_sheet> {
    public List<Stock_in_sheet> getListByAppId(long appId);
}
