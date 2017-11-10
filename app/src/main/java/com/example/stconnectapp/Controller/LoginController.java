package com.example.stconnectapp.Controller;

import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.Model.LoginHandler;
import com.example.stconnectapp.View.LogInFragment;

public class LoginController {
    private LoginHandler loginHandler;
    private LogInFragment fragment;

    public LoginController(LogInFragment fragment){
        loginHandler = new LoginHandler(this);
        this.fragment = fragment;
    }

    public void logIn(String email, String password) {
        User user = new User(email, password, null);
        loginHandler.logIn(user);
    }

    public void statusCode(int statusCode){
        fragment.getStatusCode(statusCode);
    }
}
