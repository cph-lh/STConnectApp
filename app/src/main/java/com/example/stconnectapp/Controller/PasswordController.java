package com.example.stconnectapp.Controller;

import com.example.stconnectapp.Model.PasswordHandler;
import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.View.ChangePasswordFragment;

public class PasswordController {

    private PasswordHandler handler;
    private ChangePasswordFragment fragment;

    public PasswordController(ChangePasswordFragment fragment){
        handler  = new PasswordHandler(this);
        this.fragment = fragment;
    }

    public void changePassword(String password, String passwordConfirm) {
        User user = new User(null, password, passwordConfirm);
        handler.changePassword(user);
    }

    public void statusCode(int statusCode){
        fragment.getStatusCode(statusCode);
    }
}
