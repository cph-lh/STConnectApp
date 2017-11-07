package com.example.stconnectapp.DTO;

public class User {

    String email;
    String password;
    String password_confirmation;

    public User(String email, String password, String password_confirmation) {
        this.email = email;
        this.password = password;
        this.password_confirmation = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordChange() {
        return password_confirmation;
    }

    public void setPasswordChange(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
