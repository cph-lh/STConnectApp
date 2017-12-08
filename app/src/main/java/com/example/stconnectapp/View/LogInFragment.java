package com.example.stconnectapp.View;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.stconnectapp.Model.Helper;
import com.example.stconnectapp.Model.LogInHandler;
import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.R;
import com.loopj.android.http.AsyncHttpClient;

public class LogInFragment extends Fragment {

    private View root;
    private LogInHandler handler;
    private EditText email;
    private EditText password;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.log_in_fragment, container, false);
        handler = new LogInHandler(this);

        email = root.findViewById(R.id.email);
        password = root.findViewById(R.id.password);

        Button testButton = root.findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        Button logInButton = root.findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                User user = new User(email.getText().toString(), password.getText().toString(), null);
                handler.logIn(user);
            }
        });
        return root;
    }

    public void statusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                MainMenuFragment fragment = MainMenuFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                Snackbar.make(root, "Log in successful.", Snackbar.LENGTH_LONG).show();
                break;
            case 401:
                Snackbar.make(root, "Log in failed - try again.", Snackbar.LENGTH_LONG).show();
                break;
            case 0:
                Snackbar.make(root, "Unable to connect - try again later.", Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(root, "Unexpected error occurred - try again later.", Snackbar.LENGTH_LONG).show();
                break;
        }
        password.getText().clear();
    }

}
