package com.example.stconnectapp.View;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stconnectapp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MainMenuFragment extends Fragment {

    private View root;
    private final String BASE_URL = "http://159.89.3.169:3000/auth/";
    private AsyncHttpClient client;
    private SharedPreferences sharedPreferences;

    public static MainMenuFragment newInstance(){
        return new MainMenuFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        root = inflater.inflate(R.layout.main_menu_fragment, container, false);

        Button button = root.findViewById(R.id.password_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        return root;
    }

    public AsyncHttpClient addHeaders(AsyncHttpClient client) {
        sharedPreferences = getActivity().getSharedPreferences("sp", getActivity().MODE_PRIVATE);
        client.addHeader("access-token", sharedPreferences.getString("access-token", ""));
        client.addHeader("expiry", sharedPreferences.getString("expiry", ""));
        client.addHeader("token-type", sharedPreferences.getString("token-type", ""));
        client.addHeader("uid", sharedPreferences.getString("uid", ""));
        client.addHeader("client", sharedPreferences.getString("client", ""));
        return client;
    }

    public void changePassword() {
        client = new AsyncHttpClient();
        addHeaders(client);

        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("password", "12345678");
            jsonParams.put("password_confirmation", "12345678");
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.patch(getActivity(), BASE_URL + "password", entity, "application/json",
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
}
