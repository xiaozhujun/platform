package org.whut.inspectManagement.business.inspectReport.mapper;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.whut.inspectManagement.business.inspectReport.entity.ReportSearch;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-6-10
 * Time: 上午9:25
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
