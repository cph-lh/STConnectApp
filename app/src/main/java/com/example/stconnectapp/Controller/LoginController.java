package com.example.stconnectapp.Controller;

import android.content.Context;

import com.example.stconnectapp.DTO.User;
import com.example.stconnectapp.Model.LoginHandler;

public class LoginController {

    private Context context;
    private LoginHandler loginHandler;

    public LoginController(Context context){
        this.context = context;
        loginHandler = new LoginHandler(context);
    }

    public boolean logIn(String email, String password) {
        User user = new User(email, password, null);
        return loginHandler.logIn(user);
    }
}
