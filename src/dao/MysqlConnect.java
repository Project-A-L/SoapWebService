package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            System.out.println(e.getMessage());
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            new MysqlConnect();
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