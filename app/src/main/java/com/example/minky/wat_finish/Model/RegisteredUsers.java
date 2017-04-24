package com.example.minky.wat_finish.Model;

/**
 * Created by Minky on 2017-04-23.
 */

public class RegisteredUsers {

    private String name, email, password;
    private Standing userType;

    public RegisteredUsers(String name, String email, String password, Standing userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Standing getUserType() {
        return userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(Standing userType) {
        this.userType = userType;
    }
}
