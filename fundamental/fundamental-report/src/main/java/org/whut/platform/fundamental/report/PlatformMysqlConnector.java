package org.whut.platform.fundamental.report;

import org.whut.platform.fundamental.config.FundamentalConfigProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-26
 * Time: 下午10:25
 * To change this template use File | Settings | File Templates.
 */
public class PlatformMysqlConnector {
    private static String className= FundamentalConfigProvider.get("dbcp.riskmanagement.driverClassName");
    private String url=FundamentalConfigProvider.get("dbcp.riskmanagement.url");
    private String username=FundamentalConfigProvider.get("dbcp.riskmanagement.username");
    private String password=FundamentalConfigProvider.get("dbcp.riskmanagement.password");
    static {
        try{
            Class.forName(className);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        Connection connection=null;
        try{
            connection= DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
       return connection;
    }
    public static void main(String[] args){
        PlatformMysqlConnector ds=new PlatformMysqlConnector();
        System.out.print(ds.getConnection());

    }
}
