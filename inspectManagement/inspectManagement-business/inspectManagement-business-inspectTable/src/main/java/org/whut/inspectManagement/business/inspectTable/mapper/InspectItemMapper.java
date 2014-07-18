package org.whut.inspectManagement.business.inspectTable.mapper;

import org.apache.ibatis.annotations.Param;
import org.whut.inspectManagement.business.inspectTable.entity.InspectItem;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import javax.ws.rs.FormParam;
import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: choumiaoer
 * Date: 14-5-11
 * Time: 上午10:59
 * To change this template use File | Settings | File Templates.
 */
public interface InspectItemMapper extends AbstractMapper<InspectItem> {
    public List<InspectItem> findByCondition(Map<String,Object> map);
    public void addList(List<InspectItem> inspectItemList);
    public long getInspectItemId(@Param("name") String name,@Param("number") String number,@Param("tableId") long tableId,@Param("areaId") long areaId,@Param("appId")long appId);
    public long findIdByCondition(InspectItem inspectItem);
    public long getInspectItemByNameAndNumber(@Param("name") String name,@Param("number") String number);
    public List<InspectItem> getInspectItemByInspectTableId(@Param("inspectTableId") long inspectTableId,@Param("appId") long appId);
    public List<InspectItem> getInspectItemListByAppId(long appId);
    public String findIdByTagAndName(@Param("inspecttag")String inspecttag,@Param("name")String name);
    public List<Map<String,String>> getInspectItemList(long appId);
}
