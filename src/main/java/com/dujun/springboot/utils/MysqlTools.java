/**
 * author     : dujun
 * date       : 2021/12/2 11:35
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import com.dujun.springboot.tools.YmlTools;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.sql.*;

public class MysqlTools {

    YmlTools ymlTools = new YmlTools("globalConfig.yml");
    final String JDBC_URL = ymlTools.getValueByKey("test.database.jdbcurl","");
    final String USER = ymlTools.getValueByKey("test.database.user","");
    final String PASSWORD = ymlTools.getValueByKey("test.database.password","");
    Connection connection = null;
    Statement statement = null;
    public String msg = "";
    public boolean result ;

    public MysqlTools() {
        try {
            this.connection = DriverManager.getConnection(JDBC_URL,USER,PASSWORD);
            this.statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MysqlTools(String jdbcUrl,String user,String password){
        try {
            this.connection = DriverManager.getConnection(jdbcUrl, user, password);
            this.statement = connection.createStatement();
            result = true;
        } catch (SQLException sqlException) {
//            sqlException.printStackTrace();
            System.out.println(sqlException);
            result = false;
            msg = String.valueOf(sqlException);
        }
    }

    //更新操作
    public void execute(String sql){
        try {
            this.statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //查询操作
    public ResultSet executeQuery(String sql) throws SQLException {
        ResultSet resultSet = null;
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    //关闭操作
    public void close(){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }


    }

}


class Tests{
    public static void main(String[] args) {
        MysqlTools mysqlTools = new MysqlTools();
        mysqlTools.msg = "操作成功";
    }
}