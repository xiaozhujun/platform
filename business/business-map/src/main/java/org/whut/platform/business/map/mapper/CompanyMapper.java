package org.whut.platform.business.map.mapper;
import org.whut.platform.fundamental.orm.mapper.AbstractMapper;
/**
 * Created with IntelliJ IDEA.
 * User: zhuzhenhua
 * Date: 14-3-24
 * Time: 下午3:49
 * To change this template use File | Settings | File Templates.
 */
public interface CompanyMapper extends AbstractMapper{
    public Long findIdByName(String name);
}
