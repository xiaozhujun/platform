package org.whut.inspectManagement.business.inspectTable.mapper;

import org.whut.inspectManagement.business.inspectTable.entity.InspectChoice;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: xjie
 * Date: 14-5-15
 * Time: 下午2:59
 * To change this template use File | Settings | File Templates.
 */
public interface InspectChoiceMapper extends AbstractMapper<InspectChoice> {
    public long getIdByChoiceValue(String choiceValue);
    public List<InspectChoice> getList();
}
