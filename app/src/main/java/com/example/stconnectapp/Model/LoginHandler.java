package com.example.stconnectapp.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.stconnectapp.DTO.User;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class LoginHandler {
    private AsyncHttpClient client;
    private static final String BASE_URL = "http://159.89.3.169:3000/auth/";
    private SharedPreferences sharedPreferences;
    private Context context;
    private int status;

    public LoginHandler(Context context) {
        this.context = context;
    }

    public boolean logIn(User user) {
        client = new AsyncHttpClient();

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("email", user.getEmail());
            jsonParams.put("password", user.getPassword());
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(context, BASE_URL + "sign_in", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            status = statusCode;
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                            Log.d("asd", "----------------RESPONSE: " + serverResp + Arrays.toString(headers));
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            sharedPreferences = context.getSharedPreferences("sp", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            for (int i = 0; i < headers.length; i++) {
                                editor.putString(headers[i].getName(), headers[i].getValue());
                                Log.d("asd", "------------------VALUE: " + headers[i].getValue() + " NAME: " + headers[i].getName());
                            }
                            editor.apply();
                            Log.d("asd", "---------------EXPECTED: " + sharedPreferences.getString("access-token", ""));
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (status == 200)
        {
            return true;
        } else return false;
    }
}
