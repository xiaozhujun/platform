package org.whut.platform.fundamental.report;

import org.whut.platform.fundamental.config.FundamentalConfigProvider;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: ThinkPad
 * Date: 14-3-26
 * Time: 下午10:25
 * To change this template use File | Settings | File Templates.
 */
public class PlatformMysqlConnector {
    private static String className= null;
    private String url=null;
    private String username=null;
    private String password=null;
    PlatformMysqlConnector(String className,String url,String username,String password){
        this.className=className;
        this.url=url;
        this.username=username;
        this.password=password;
    }
    public Connection getConnection(){
        Connection connection=null;
        try{
            try{
                Class.forName(className);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            connection= DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
       return connection;
    }
    public void close(Connection connection,PreparedStatement statement,ResultSet rs){
        if(rs!=null){
            try{
               rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try{
              statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){

    }
}
