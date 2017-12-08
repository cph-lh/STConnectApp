package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stconnectapp.Model.AuthenticationHandler;
import com.example.stconnectapp.R;

public class StartFragment extends Fragment {

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    private View root;
    private AuthenticationHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.start_fragment, container, false);
        handler = new AuthenticationHandler(this);
        handler.CheckLogIn();
        return root;
    }

    public void CheckLogInStatusCode(int statusCode) {
        switch (statusCode) {
            case 200:
                MainMenuFragment f200 = MainMenuFragment.newInstance();
                getFragmentManager().beginTransaction().add(R.id.fragment_container, f200).commit();
                break;
            case 401:
                Snackbar.make(root, "Session expired", Snackbar.LENGTH_LONG).show();
                LogInFragment f401 = LogInFragment.newInstance();
                getFragmentManager().beginTransaction().add(R.id.fragment_container, f401).commit();
                break;
            case 0:
                Snackbar.make(root, "Unable to connect - try again later.", Snackbar.LENGTH_LONG).show();
                LogInFragment f0 = LogInFragment.newInstance();
                getFragmentManager().beginTransaction().add(R.id.fragment_container, f0).commit();
                break;
            default:
                Snackbar.make(root, "Unexpected error occurred - try again later.", Snackbar.LENGTH_LONG).show();
                LogInFragment f = LogInFragment.newInstance();
                getFragmentManager().beginTransaction().add(R.id.fragment_container, f).commit();
                break;
        }
    }
}
