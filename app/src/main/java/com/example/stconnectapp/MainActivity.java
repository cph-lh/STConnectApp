package com.example.stconnectapp;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.stconnectapp.Model.AuthenticationHandler;
import com.example.stconnectapp.Model.Helper;
import com.example.stconnectapp.View.LogInFragment;
import com.example.stconnectapp.View.MainMenuFragment;
import com.example.stconnectapp.View.StartFragment;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private AuthenticationHandler handler;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Helper.setContext(this);
        sharedPreferences = Helper.getContext().getSharedPreferences("sp", Helper.getContext().MODE_PRIVATE);
        if (savedInstanceState == null) {
            StartFragment startFragment = StartFragment.newInstance();
            getFragmentManager().beginTransaction().add(R.id.fragment_container, startFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_log_out:
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().apply();
                LogInFragment fragment = LogInFragment.newInstance();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}