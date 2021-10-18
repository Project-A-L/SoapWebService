package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.User;


public class UserRepository {
    private static Connection sql;

    public UserRepository() {
        sql = MysqlConnect.getConnection();
    }

    public void save(User user) {
        try {
            PreparedStatement query = sql.prepareStatement(
                    "INSERT INTO Users(mail,firstName,lastName,phoneNumber,userRole,userLogin,userPassword) VALUES(?,?,?,?,?,?,?)");
            query.setString(1, user.getEmail());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getPhoneNumber());
            query.setString(5, user.getUserRole());
            query.setString(6, user.getLogin());
            query.setString(7, user.getPassword());
            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users");
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new User(result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),
                        result.getString("userLogin"), result.getBoolean("isBlocked")));
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return users;
        }
    }
}