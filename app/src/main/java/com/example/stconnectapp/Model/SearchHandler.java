package com.example.stconnectapp.Model;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.stconnectapp.View.SearchFragment;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class SearchHandler {

    private static final String BASE_URL = "http://159.89.3.169:3000/";
    private SharedPreferences sharedPreferences;
    private AsyncHttpClient client;
    private SearchFragment searchFragment;

    public SearchHandler(SearchFragment search) {
        this.searchFragment = search;
    }

    public AsyncHttpClient setHeaders(AsyncHttpClient client) {

        sharedPreferences = Helper.getContext().getSharedPreferences("sp", Helper.getContext().MODE_PRIVATE);
        client.addHeader("access-token", sharedPreferences.getString("access-token", ""));
        client.addHeader("expiry", sharedPreferences.getString("expiry", ""));
        client.addHeader("token-type", sharedPreferences.getString("token-type", ""));
        client.addHeader("uid", sharedPreferences.getString("uid", ""));
        client.addHeader("client", sharedPreferences.getString("client", ""));
        Log.d("Access-token", "" + sharedPreferences.getString("access-token", ""));
        Log.d("Expiry", "" + sharedPreferences.getString("expiry", ""));
        Log.d("Token-Type", "" + sharedPreferences.getString("token-type", ""));
        Log.d("UId", "" + sharedPreferences.getString("uid", ""));
        Log.d("Client", "" + sharedPreferences.getString("client", ""));
        return client;
    }

    public void searchFilter(Search search) throws JSONException {
        client = new AsyncHttpClient();
        setHeaders(client);

        try {
            JSONObject wrap = new JSONObject();
            wrap.put("name", search.getName());
            wrap.put("email", search.getEmail());
            wrap.put("education", search.getEducation());
            wrap.put("experience", search.getExperience());

            Log.d("Search penus","Skills name:"+search.getSkill().get(0).getName());
            JSONArray searchSkill = new JSONArray();
            searchSkill.put(search.getSkill().get(0).getName());
            wrap.put("skill", searchSkill);

            Log.d("handler Skill","Skills:"+searchSkill.get(0).toString());
            JSONObject json = new JSONObject();
            json.put("filter", wrap);
            Log.d("HANDLER", "---------JSON" + json.toString());

            StringEntity entity = new StringEntity(json.toString());
            client.post(Helper.getContext(), BASE_URL + "users/1/user_searches", entity, "application/json",
                    new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Log.d("HANDLER", "-------------RESPONSE: " + response);
                            Gson gson = new Gson();
                            Profile user = gson.fromJson(response.toString(), Profile.class);
                            Log.d("HANDLER", "USER" + user.profiles.get(0).name);
                            Log.d("HANDLER", "SEARCH-------------STATUS: " + statusCode);
                            searchFragment.searchResult(statusCode, user.profiles);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            super.onFailure(statusCode, headers, responseString, throwable);
                            Log.d("FAIL","HANDLER ERROR-----STATUS:"+statusCode);
                            searchFragment.searchResult(statusCode, null);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
