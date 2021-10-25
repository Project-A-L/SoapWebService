package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import domain.User;
import exceptions.DbConnectionException;
import exceptions.DeleteException;
import exceptions.FetchException;
import exceptions.SaveException;
import exceptions.UpdateException;
import exceptions.UserAlreadyExistException;


public class UserRepository {
    private static Connection sql = null;
    private static UserRepository instance = new UserRepository();

    private UserRepository() {
        try {
            sql = MysqlConnect.getConnection();
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public static UserRepository getUserRepoInstance() throws DbConnectionException {
        if (sql != null) {
            return instance;
        }
        else {
            throw new DbConnectionException("No Sql Connection Found !");
        }
    }
    
    public void save(User user) throws SaveException, UserAlreadyExistException {
        try {
            User userExist = findOne(user.getEmail());
            if (userExist == null) {
                try {
                    PreparedStatement query = sql.prepareStatement(
                        "INSERT INTO Users(mail,firstName,lastName,phoneNumber,userRole,userPassword) VALUES(?,?,?,?,?,?)");
                        query.setString(1, user.getEmail());
                        query.setString(2, user.getFirstName());
                        query.setString(3, user.getLastName());
                        query.setString(4, user.getPhoneNumber());
                        query.setString(5, user.getUserRole());
                        query.setString(6, user.getPassword());
                        query.executeUpdate();
                } catch (SQLException e) {
                    throw new SaveException("An error occured while adding a new user ! : " + e.getMessage());
                }
            } else {
                throw new UserAlreadyExistException("User with email " + user.getEmail() + " already exist !");
            }
        } catch (FetchException e) {}
        
    }

    public ArrayList<User> findAll() throws FetchException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users");
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt("id"), result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),result.getBoolean("isBlocked")));
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
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),result.getBoolean("isBlocked"));
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
                        result.getBoolean("isBlocked"));
                return user;
            } else {
                throw new FetchException("User with mail " + mail + " Not Found !");
            }
        } catch (SQLException e) {
            throw new FetchException(
                    "An error occured while fetching user with email " + mail + " ! : " + e.getMessage());
        }
    }
    
    public User findOne(String mail) throws FetchException {
        User user = null;
        try {
            PreparedStatement query = sql.prepareStatement("SELECT userPassword,userRole FROM Users WHERE mail = ?");
            query.setString(1, mail);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                user = new User(mail,result.getString("userPassword"),result.getString("userRole"));
            }
            return user;
        } catch (SQLException e) {
            throw new FetchException(
                    "An error occured while fetching user with email " + mail + " ! : " + e.getMessage());
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
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),result.getBoolean("isBlocked")));
            }
            return users;
        } catch (SQLException e) {
            throw new FetchException("An error occured while fetching users with role " + role + " ! : " + e.getMessage());
        }
    }
    
    public void update(User user) throws UpdateException{
        try {
            PreparedStatement query = sql.prepareStatement(
                    "UPDATE users SET mail=?,firstName=?,lastName=?,phoneNumber=?,userRole=?,userPassword=?,dateModified=NOW() WHERE id=?");
            query.setString(1, user.getEmail());
            query.setString(2, user.getFirstName());
            query.setString(3, user.getLastName());
            query.setString(4, user.getPhoneNumber());
            query.setString(5, user.getUserRole());
            query.setString(6, user.getPassword());
            query.setInt(7, user.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new UpdateException("An error Occured while updating user ! : " + e.getMessage());
        }
    }
    
    public void blockUser(int id,boolean block) throws UpdateException {
        try {
            PreparedStatement query = sql.prepareStatement(
                    "UPDATE users SET isBlocked = ?,dateModified=NOW() WHERE id=?");
            query.setInt(1, block ? 1 : 0);
            query.setInt(2, id);
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
    
    public void deleteByMail(String mail) throws DeleteException {
        try {
            PreparedStatement query = sql.prepareStatement("DELETE FROM users where mail=?");
            query.setString(1, mail);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("An error occured while deleting user ! : " + e.getMessage());
        }
    }

    public ArrayList<User> filter(String string) throws FetchException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement query = sql
                    .prepareStatement("SELECT * FROM Users WHERE firstName LIKE ? OR lastName LIKE ? OR mail LIKE ?");
            String search = string + "%";
            query.setString(1, search);
            query.setString(2, search);
            query.setString(3, search);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt("id"), result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"), result.getString("phoneNumber"), result.getString("userRole"),result.getBoolean("isBlocked")));
            }
            return users;
        } catch (SQLException e) {
            throw new FetchException("An error occured while filtering users ! " + e.getMessage());
        }
    }

}