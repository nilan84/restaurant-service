package lk.uoc.mit.restaurant.mysql.model;

import lk.uoc.mit.restaurant.mysql.config.UserType;

import javax.validation.constraints.Size;

/**
 * Created by nilan on 8/1/15.
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    @Size(min = 1, max = 255, message="User Name Can Not be null")
    private String username;
    @Size(min = 6, max = 100, message="Password must be at least 6 characters")
    private String password;
    private UserType userType;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
