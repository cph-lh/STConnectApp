package com.example.stconnectapp.View;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.stconnectapp.Controller.PasswordController;
import com.example.stconnectapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ChangePasswordFragment extends Fragment {

    private View root;
    private PasswordController controller;
    private EditText passwordForm;
    private EditText passwordConfirmationForm;
    private String password;
    private String passwordConfirmation;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        root = inflater.inflate(R.layout.change_password_fragment, container, false);

        passwordForm = root.findViewById(R.id.password);
        passwordConfirmationForm = root.findViewById(R.id.password_confirmation);

        Button button = root.findViewById(R.id.password_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = passwordForm.getText().toString();
                passwordConfirmation = passwordConfirmationForm.getText().toString();
                controller.changePassword(password,passwordConfirmation);
            }
        });
        return root;
    }

    //****METODE TIL AT FÅ STATUS CODE****
}
