package domain;



import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
    
    private String email;
    private String fisrtName;
    private String lastName;
    private String phoneNumber;
    private String userRole;
    private String login;
    private String password;
    private boolean blocked;
    private Date dateCreated;
    private Date dateModified;

    public User() {}
    
    public User(String email, String firstName, String lastName, String phoneNumber, String userRole, String login,
            String password) {
        super();
        setEmail(email); 
        setFisrtName(fisrtName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setUserRole(userRole);
        setLogin(login);
        setPassword(password);
        setBlocked(false);
        setDateCreated(new Date());
        setDateModified(new Date());
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    
    public String getEmail() {
        return email;
    }
    
    public String getFisrtName() {
        return fisrtName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserRole() {
        return userRole;
    }
    
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isBlocked() {
        return blocked;
    }
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }
}

