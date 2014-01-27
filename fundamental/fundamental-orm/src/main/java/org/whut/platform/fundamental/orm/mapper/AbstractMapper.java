package org.whut.platform.fundamental.orm.mapper;

import java.util.List;
import java.util.Map;

/**
 *
 * @author quanxiwei
 *
 * @param <T>
 */
public interface AbstractMapper<T> {
    /**
     * 插入对象
     *
     * @param t
     */
    void add(T t);

    /**
     * 更新对象
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 删除对象
     *
     * @param t
     * @return
     */
    int delete(T t);

    /**
     * 根据id查找对象
     *
     * @param t
     * @return
     */
    T get(T t);

    /**
     * 根据条件分页查询
     *
     * @param conditions
     * @return
     */
    List<T> find(Map<String, Object> conditions);

}
