package com.example.stconnectapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.stconnectapp.Controller.LoginController;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    private final String BASE_URL = "http://159.89.3.169:3000/auth/";
    private AsyncHttpClient client;
    private LoginController controller;
    private String email;
    private String password;
    private EditText emailForm;
    private EditText passwordForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        controller = new LoginController(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailForm = findViewById(R.id.email);
        passwordForm = findViewById(R.id.password);

        Button logInButton = findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailForm.getText().toString();
                password = passwordForm.getText().toString();
                if (controller.logIn(email, password)) {
                    Snackbar.make(view, "Log in successful", Snackbar.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void changePassword() {
        client = new AsyncHttpClient();

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("password", "12345678");
            jsonParams.put("password_confirmation", "12345678");
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.patch(this, BASE_URL + "password", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // If the response is JSONObject instead of expected JSONArray
                            Log.d("asd", "-------------RESPONSE: " + response);
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                            // Pull out the first event on the public timeline
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}