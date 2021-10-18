package domain;



import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
    private int id;
    private String email;
    private String firstName;
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
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setUserRole(userRole);
        setLogin(login);
        setPassword(password);
        setBlocked(false);
        setDateCreated(new Date());
        setDateModified(new Date());
    }

    public User(int id, String email, String firstName, String lastName, String phoneNumber, String userRole,
            String login, boolean blocked) {
        super();
        setId(id);
        setFirstName(firstName);
        setEmail(email);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setUserRole(userRole);
        setLogin(login);
        setBlocked(blocked);
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public int getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getFirstName() {
        return firstName;
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

