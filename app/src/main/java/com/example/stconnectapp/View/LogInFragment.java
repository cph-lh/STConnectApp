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
import com.loopj.android.http.AsyncHttpClient;

public class LogInFragment extends Fragment {

    private View root;
    private LoginController controller;
    private String email;
    private String password;
    private EditText emailForm;
    private EditText passwordForm;
    private boolean success;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        controller = new LoginController(getActivity());

        root = inflater.inflate(R.layout.log_in_fragment, container, false);

        emailForm = root.findViewById(R.id.email);
        passwordForm = root.findViewById(R.id.password);

        Button logInButton = root.findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                email = emailForm.getText().toString();
                password = passwordForm.getText().toString();
                success = controller.logIn(email, password);
                final long delay = 1500L;
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(success)
                            Snackbar.make(view, "Log in successful", Snackbar.LENGTH_SHORT).show();
                        MainMenuFragment fragment = new MainMenuFragment();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                    }
                }, delay);
            }
        });
        return root;
    }
}
