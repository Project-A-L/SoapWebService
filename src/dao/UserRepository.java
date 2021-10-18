package dao;

import java.util.ArrayList;

import domain.User;


public class UserRepository {
    private ArrayList<User> users;

    public UserRepository() {
        this.users = new ArrayList<User>();
    }
    public void save(User user) {
        users.add(user);
    }
}