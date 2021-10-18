package services;

import javax.jws.WebService;

import dao.UserRepository;

@WebService
public class UserAuthentication {
    private UserRepository userRepo;

    public UserAuthentication() {
        this.userRepo = UserRepository.getUserRepoInstance();
    }
}
