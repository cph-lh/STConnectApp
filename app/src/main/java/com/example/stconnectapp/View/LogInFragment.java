package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.stconnectapp.Controller.LoginController;
import com.example.stconnectapp.R;

public class LogInFragment extends Fragment {

    private View root;
    private LoginController controller;
    private EditText email;
    private EditText password;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        controller = new LoginController(this);
        root = inflater.inflate(R.layout.log_in_fragment, container, false);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);

        Button logInButton = root.findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                controller.logIn(email.getText().toString(), password.getText().toString());
            }
        });
        return root;
    }

    public void getStatusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                MainMenuFragment fragment = MainMenuFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                Snackbar.make(root, "Log in successful.", Snackbar.LENGTH_LONG).show();
                break;
            case 401:
                Snackbar.make(root, "Log in failed - try again.", Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(root, "An error occurred.", Snackbar.LENGTH_LONG).show();
                break;
        }
        password.getText().clear();
    }
}
