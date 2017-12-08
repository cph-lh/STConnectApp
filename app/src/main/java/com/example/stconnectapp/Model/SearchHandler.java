package com.example.stconnectapp.Model;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.stconnectapp.View.SearchFragment;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SearchHandler {

    private static final String BASE_URL = "http://159.89.3.169:3000/";
    private SharedPreferences sharedPreferences;
    private AsyncHttpClient client;
    private SearchFragment fragment;

    public SearchHandler(SearchFragment fragment) {
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

    public void searchFilter(Search search) throws JSONException {
        client = new AsyncHttpClient();
        setHeaders(client);
        //users/1/user_searches"
        //"?filter=name&value=tim
        try {
            JSONObject wrap = new JSONObject();
            wrap.put("name", search.getName());
            JSONObject json = new JSONObject();
            json.put("filter", wrap);
            Log.d("asd", "---------JSON" + json.toString());
            StringEntity entity = new StringEntity(json.toString());
            client.post(Helper.getContext(), BASE_URL + "users/1/user_searches", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("asd", "-------------RESPONSE: " + response);
                            try {
                                JSONObject serverResp = new JSONObject(response.toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //fragment.statusCode(statusCode);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                            //fragment.statusCode(statusCode);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
