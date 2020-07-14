package com.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutils {
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JDBCutils.class.getResourceAsStream("/jdbc.properties");
            properties.load(inputStream);
            URL = properties.getProperty("URL");
            USERNAME = properties.getProperty("USERNAME");
            PASSWORD = properties.getProperty("PASSWORD");
            DRIVER = properties.getProperty("DRIVER");
            Class.forName(DRIVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //创建连接
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("数据库连接失败");
        }
        return conn;
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws Exception{
        if(resultSet != null){
            resultSet.close();
        }

        if(statement!=null){
            statement.close();
        }

        if(connection != null){
            connection.close();
        }
    }


}
