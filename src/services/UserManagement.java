package services;

import java.util.ArrayList;

import javax.jws.WebService;
import javax.jws.WebParam;
import dao.UserRepository;
import domain.User;
import exceptions.DbConnectionException;
import exceptions.DeleteException;
import exceptions.FetchException;
import exceptions.SaveException;
import exceptions.UpdateException;
import exceptions.UserAlreadyExistException;


@WebService
public class UserManagement {
    private UserRepository userRepo;

    public UserManagement() {
        try {
            this.userRepo = UserRepository.getUserRepoInstance();            
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    public String addUser(@WebParam(name = "User") User user) {
        try {
            userRepo.save(user);
            return "User Added Successfully !";
        } catch (SaveException e) {
            return e.getMessage();
        } catch(UserAlreadyExistException e) {
            return e.getMessage();
        }
    }
    
    public ArrayList<User> getAllUsers() {
        try {
            return userRepo.findAll();
        } catch (FetchException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<User> getUsersByRole(@WebParam(name = "Role") String role) {
        try {
            return userRepo.findByRole(role);
        } catch (FetchException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<User> filterUsers(@WebParam(name = "Query") String query) {
        try {
            return userRepo.filter(query);
        } catch (FetchException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getUserById(@WebParam(name = "Id") int id) {
        try {
            return userRepo.findById(id);
        } catch (FetchException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public User getUserByMail(@WebParam(name = "Mail") String  mail) {
        try {
            return userRepo.findByMail(mail);
        } catch (FetchException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public String updateUser(@WebParam(name = "User") User user) {
        try {
            userRepo.update(user);
            return "User Modified Successfully !";
        } catch (UpdateException e) {
            return e.getMessage();
        }
    }

    public String deleteUserById(@WebParam(name = "Id") int id) {
        try {
            userRepo.deleteById(id);
            return "User deleted Successfully !";
        } catch (DeleteException e) {
            return e.getMessage();
        }
    }

    public String deleteUserByMail(@WebParam(name = "Mail") String email) {
        try {
            userRepo.deleteByMail(email);
            return "User deleted Successfully !";
        } catch (DeleteException e) {
            return e.getMessage();
        }
    }

    public String blockUser(@WebParam(name = "Id") int id, @WebParam(name = "Block") boolean block) {
        try {
            userRepo.blockUser(id, block);
            return "User Blocked Successfully !";
        } catch (UpdateException e) {
            return e.getMessage();
        }
    }
    


}
