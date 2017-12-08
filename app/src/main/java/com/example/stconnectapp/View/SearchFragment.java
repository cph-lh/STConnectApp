package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.stconnectapp.Model.SearchHandler;
import com.example.stconnectapp.Model.Search;
import com.example.stconnectapp.R;

import org.json.JSONException;

public class SearchFragment extends Fragment {

    private SearchHandler handler;
    private View root;
    private TextView name, email;
    private Button search;


    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.search_fragment, container, false);

        handler = new SearchHandler(this);

        name = root.findViewById(R.id.search_name);
        email = root.findViewById(R.id.search_email);
        search = root.findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    search();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    public void search() throws JSONException {
        Search search = new Search();
        if(!TextUtils.isEmpty(name.getText()))
        {
            search.setName(name.getText().toString());
        }
        if(!TextUtils.isEmpty(email.getText()))
        {
            search.setEmail(email.getText().toString());
        }
        handler.searchFilter(search);
    }

    public void statusCode(int StatusCode)
    {

    }
}
