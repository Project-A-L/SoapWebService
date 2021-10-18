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
    public void addUser(User user) {
        System.out.println("Creating new User!");
        try {
            userRepo.save(user);
            System.out.println("User Added Successfully !");
        } catch (Exception e) {
            System.out.println("An error occured while adding a new user ! : " + e.getMessage());
        }
    }
}
