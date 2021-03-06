package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.stconnectapp.Model.PasswordHandler;
import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.R;

public class ChangePasswordFragment extends Fragment {

    private View root;
    private PasswordHandler handler;
    private EditText password;
    private EditText passwordConfirmation;

    public static ChangePasswordFragment newInstance() {
        return new ChangePasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.change_password_fragment, container, false);

        getActivity().setTitle("Change Password");
        handler = new PasswordHandler(this);

        password = root.findViewById(R.id.password);
        passwordConfirmation = root.findViewById(R.id.password_confirmation);

        Button button = root.findViewById(R.id.password_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(null, password.getText().toString(), passwordConfirmation.getText().toString());
                handler.changePassword(user);
            }
        });
        return root;
    }

    public void statusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                MainMenuFragment fragment = MainMenuFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
                Snackbar.make(root, "Changed password successfully.", Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(root, "An error occurred.", Snackbar.LENGTH_LONG).show();
                break;
        }
        password.getText().clear();
        passwordConfirmation.getText().clear();
    }
}
