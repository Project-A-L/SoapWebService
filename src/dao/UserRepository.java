package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import domain.User;
import exceptions.DeleteException;
import exceptions.FetchException;
import exceptions.SaveException;
import exceptions.UpdateException;


public class UserRepository {
    private static Connection sql;

    public UserRepository() {
        sql = MysqlConnect.getConnection();
    }

    public void save(User user) throws SaveException{
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
            throw new SaveException("An error occured while adding a new user ! : " + e.getMessage());
        }
    }

    public ArrayList<User> findAll() throws FetchException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users");
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt("id"), result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),
                        result.getString("userLogin"), result.getBoolean("isBlocked")));
            }
            return users;
        } catch (SQLException e) {
            throw new FetchException("An error occured while fetching users ! : " + e.getMessage());
        }
    }

    public User findById(int id) throws FetchException {
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users WHERE id = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                User user = new User(result.getInt("id"), result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),
                        result.getString("userLogin"), result.getBoolean("isBlocked"));
                return user;
            } else {
                throw new FetchException("User with id " + id + " Not Found !");
            }
        } catch (SQLException e) {
            throw new FetchException("An error occured while fetching user with id " + id + " ! : " + e.getMessage());
        }
    }

    public User findByMail(String mail) throws FetchException {
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users WHERE mail = ?");
            query.setString(1, mail);
            ResultSet result = query.executeQuery();
            if (result.next()) {   
                User user = new User(result.getInt("id"), result.getString("mail"), result.getString("firstName"),
                result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),
                        result.getString("userLogin"), result.getBoolean("isBlocked"));
                return user;
            } else {
                throw new FetchException("User with mail " + mail + " Not Found !"); 
            }
        } catch (SQLException e) {
            throw new FetchException("An error occured while fetching user with email " + mail + " ! : " + e.getMessage());
        }
    }
    
    public ArrayList<User> findByRole(String role) throws FetchException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users WHERE userRole = ?");
            query.setString(1, role);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt("id"), result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),
                        result.getString("userLogin"), result.getBoolean("isBlocked")));
            }
            return users;
        } catch (SQLException e) {
            throw new FetchException("An error occured while fetching users with role " + role + " ! : " + e.getMessage());
        }
    }
    
    public void update(User user) throws UpdateException{
        try {
            PreparedStatement query = sql.prepareStatement(
                    "UPDATE users SET mail=?,firstName=?,lastName=?,phoneNumber=?,userRole=?,userLogin=?,userPassword=?,dateModified=NOW() WHERE id=?");
            query.setString(1, user.getEmail());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getPhoneNumber());
            query.setString(5, user.getUserRole());
            query.setString(6, user.getLogin());
            query.setString(7, user.getPassword());
            query.setInt(8, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("An error Occured while updating user ! : " + e.getMessage());
        }
    }
    
    public void deleteById(int id) throws DeleteException{
        try {
            PreparedStatement query = sql.prepareStatement("DELETE FROM users where id=?");
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("An error occured while deleting user ! : " + e.getMessage());
        }
    }
    
    public void deleteByMail(String mail) throws DeleteException{
        try {
            PreparedStatement query = sql.prepareStatement("DELETE FROM users where mail=?");
            query.setString(1, mail);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("An error occured while deleting user ! : " + e.getMessage());
        }
    }
}