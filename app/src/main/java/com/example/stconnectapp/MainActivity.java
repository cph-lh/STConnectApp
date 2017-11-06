package com.example.stconnectapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://159.89.3.169:3000/auth/";
    private SharedPreferences sharedPreferences;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                Snackbar.make(view, "Trying to sign in", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        Button passwordButton = findViewById(R.id.password_button);
        passwordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSearch();
            }
        });
    }

    public void signIn() {
        client = new AsyncHttpClient();

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", "tim@123.dk");
            jsonParams.put("password", "11112222");
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(this, BASE_URL + "sign_in", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            // If the response is JSONObject instead of expected JSONArray
                            Log.d("asd", "----------------RESPONSE: " + Arrays.toString(headers));
                            //headerArray = new Header[13];
                            //headerArray = headers;
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            sharedPreferences = getSharedPreferences("sp", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            for (int i = 0; i < headers.length; i++) {
                                editor.putString(headers[i].getName(), headers[i].getValue());
                                Log.d("asd", "------------------VALUE: " + headers[i].getValue() + " NAME: " + headers[i].getName());
                            }
                            editor.apply();

                            Log.d("asd", "---------------EXPECTED: " + sharedPreferences.getString("access-token", ""));
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

    public void addHeaders(AsyncHttpClient client) {
        client.addHeader("access-token", sharedPreferences.getString("access-token", ""));
        client.addHeader("expiry", sharedPreferences.getString("expiry", ""));
        client.addHeader("token-type", sharedPreferences.getString("token-type", ""));
        client.addHeader("uid", sharedPreferences.getString("uid", ""));
        client.addHeader("client", sharedPreferences.getString("client", ""));
    }

    public void changePassword() {
        client = new AsyncHttpClient();
        addHeaders(client);

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

    public void userSearch() {
        client = new AsyncHttpClient();

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