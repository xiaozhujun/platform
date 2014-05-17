package org.whut.inspectManagement.business.inspectTable.mapper;

import org.whut.inspectManagement.business.inspectTable.entity.InspectItem_choice;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;

import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: xjie
 * Date: 14-5-15
 * Time: 下午3:01
 * To change this template use File | Settings | File Templates.
 */
public interface InspectItem_ChoiceMapper extends AbstractMapper<InspectItem_choice> {
    public void addList(List<InspectItem_choice> inspectItem_choices);
}
