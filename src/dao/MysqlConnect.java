package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.DbConnectionException;

public class MysqlConnect {
    private static final String LOGIN_STRING = "admin";
    private static final String PASSWORD_STRING = "passer";
    private static final String MYSQL_DRIVER_STRING = "com.mysql.cj.jdbc.Driver";
    private static final String MYSQL_DB_STRING = "jdbc:mysql://localhost/project_al";
    private static Connection connection = null;

    private MysqlConnect() {
        try {
            Class.forName(MYSQL_DRIVER_STRING);
            connection = DriverManager.getConnection(MYSQL_DB_STRING, LOGIN_STRING, PASSWORD_STRING);
        } catch (ClassNotFoundException e) {
            System.out.println("Connection to MySql Failed : " + e.getMessage());
        } catch (SQLException se) {
            System.out.println("Connection to MySql Failed : " + se.getMessage());
        }
    }

    public static Connection getConnection() throws DbConnectionException{
        if (connection != null) {
            return connection;
        } else {
            new MysqlConnect();
            if (connection == null)
                throw new DbConnectionException("Connection to Database Failed !");
            else
            return connection;
        }
    }
    
    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());   
        }
    }

}