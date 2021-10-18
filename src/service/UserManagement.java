package service;

import javax.jws.WebService;

import dao.UserRepository;
import domain.User;


@WebService
public class UserManagement {
    private UserRepository userRepo;

    public UserManagement() {
        this.userRepo = new UserRepository();
    }
    public String addUser(User user) {
        try {
            userRepo.save(user);
            return "User Added Successfully !";
        } catch (Exception e) {
            return "An error occured while adding a new user ! : " + e.getMessage();
        }
    }
}
