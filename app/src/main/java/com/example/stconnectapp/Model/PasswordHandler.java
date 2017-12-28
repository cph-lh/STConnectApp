package com.example.stconnectapp.Model;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.stconnectapp.View.ChangePasswordFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class PasswordHandler {

    private static final String BASE_URL = "http://159.89.3.169:3000/auth/";
    private SharedPreferences sharedPreferences;
    private ChangePasswordFragment fragment;
    private AsyncHttpClient client;

    public PasswordHandler(ChangePasswordFragment fragment){
        this.fragment = fragment;
    }

    public AsyncHttpClient setHeaders(AsyncHttpClient client) {
        sharedPreferences = Helper.getContext().getSharedPreferences("sp", Helper.getContext().MODE_PRIVATE);
        client.addHeader("access-token", sharedPreferences.getString("access-token", ""));
        client.addHeader("expiry", sharedPreferences.getString("expiry", ""));
        client.addHeader("token-type", sharedPreferences.getString("token-type", ""));
        client.addHeader("uid", sharedPreferences.getString("uid", ""));
        client.addHeader("client", sharedPreferences.getString("client", ""));
        return client;
    }

    public void changePassword(User user) {
        client = new AsyncHttpClient();
        setHeaders(client);
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("password", user.getPassword());
            jsonParams.put("password_confirmation", user.getPasswordConfirmation());
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.patch(Helper.getContext(), BASE_URL + "password", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("asd", "-------------RESPONSE: " + response);
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            fragment.statusCode(statusCode);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            fragment.statusCode(statusCode);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
