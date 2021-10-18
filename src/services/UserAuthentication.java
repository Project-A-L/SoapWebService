package services;

import javax.jws.WebParam;
import javax.jws.WebService;

import dao.UserRepository;
import domain.User;
import exceptions.FetchException;
import security.Crypt;

@WebService
public class UserAuthentication {
    private UserRepository userRepo;

    public UserAuthentication() {
        this.userRepo = UserRepository.getUserRepoInstance();
    }

    public boolean login(@WebParam(name = "Mail")String mail,@WebParam(name = "Password") String password) {
        try {
            User user = userRepo.findOne(mail);
            if (user.getPassword().equalsIgnoreCase(Crypt.encrypt(password))) {
                return true;
            }
            else
                return false;
        } catch (FetchException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
