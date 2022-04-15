/**
 * author     : dujun
 * date       : 2021/12/2 11:35
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import com.dujun.springboot.tools.YmlTools;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javafx.scene.Parent;
import org.apache.http.client.methods.CloseableHttpResponse;

import javax.sql.DataSource;
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
    HikariConfig config = new HikariConfig();


    public MysqlTools() {
        config.setJdbcUrl(JDBC_URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);
        try {
            connection = ds.getConnection();
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public MysqlTools(String jdbcUrl,String user,String password){
        try {
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(user);
            config.setPassword(password);
            config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
            config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
            config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
            DataSource ds = new HikariDataSource(config);
            connection = ds.getConnection();
            statement = connection.createStatement();
            result = true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            result = false;
            msg = String.valueOf(sqlException);
        }
    }

    //更新操作
    public void execute(String sql){
        System.out.println("打印下sql"+sql);
        try {
            this.statement.execute(sql);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    //查询操作
    public ResultSet executeQuery(String sql){
        ResultSet resultSet = null;
        try {
            resultSet = connection.prepareStatement(sql).executeQuery();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
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
        String sql = "select * from crm.crm_config WHERE name = 'crm_electricity_status'";
        ResultSet resultSet = mysqlTools.executeQuery(sql);
        try {
            if (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}