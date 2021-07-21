package fabianfagan.userservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * For storing information about a user. 
 * This User object is defined as an entity for JPA storage, with the email being the ID. 
 * @author Fabian Fagan
 */
@Entity
public class User {
    private @Id String email; 
    private String password; 
    private String firstName; 
    private String lastName; 
    
    //Constructors

    User() {} 

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    //Getters & Setters

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail(String email ) {
        this.email = email; 
    }

    public void setPassword(String password) {
        this.password = password; 
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }
    
    @Override
    public String toString() {
        return "{" +
            " email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            "}";
    }
   
}
