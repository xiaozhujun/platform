package org.whut.inspectManagement.business.inspectReport.mapper;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-12
 * Time: 下午9:57
 * To change this template use File | Settings | File Templates.
 */
public class ReportSqlMapper {
    public String getSql(String id,SqlSessionFactory sqlSessionFactory,Map<String,Object> parameterMap){
        String sql=null;
        MappedStatement ms=sqlSessionFactory.getConfiguration().getMappedStatement(id);
        BoundSql boundSql=ms.getBoundSql(parameterMap);
        sql=boundSql.getSql();
        return sql;
    }
}
