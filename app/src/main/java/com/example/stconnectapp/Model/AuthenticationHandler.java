package com.example.stconnectapp.Model;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.stconnectapp.MainActivity;
import com.example.stconnectapp.View.StartFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.conn.ConnectTimeoutException;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AuthenticationHandler {


    private AsyncHttpClient client;
    private static final String BASE_URL = "http://159.89.3.169:3000/auth/";
    private SharedPreferences sharedPreferences;
    private StartFragment fragment;


    public AuthenticationHandler(StartFragment fragment){
        this.fragment = fragment;
    }

    public void CheckLogIn() {
        client = new AsyncHttpClient();
        setHeaders(client);
        JSONObject jsonParams = new JSONObject();
        try {
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.get(Helper.getContext(), BASE_URL + "validate_token", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                                Log.d("asd", "-------------Status: " + statusCode);
                                fragment.CheckLogInStatusCode(statusCode);
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }
                        }
                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Log.d("asd",""+statusCode);
                            fragment.CheckLogInStatusCode(statusCode);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AsyncHttpClient setHeaders(AsyncHttpClient client) {
        sharedPreferences = Helper.getContext().getSharedPreferences("sp", Helper.getContext().MODE_PRIVATE);
        client.addHeader("access-token", sharedPreferences.getString("access-token", ""));
        client.addHeader("uid", sharedPreferences.getString("uid", ""));
        client.addHeader("client", sharedPreferences.getString("client", ""));
        return client;
    }
}
