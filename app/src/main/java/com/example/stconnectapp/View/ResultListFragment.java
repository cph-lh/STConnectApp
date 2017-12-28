package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultListFragment extends Fragment {

    private View root;
    private ListView listView;
    private TextView errorText;
    private ImageView errorIcon;
    private ResultListAdapter adapter;
    private ArrayList<User> users;
    private int status;

    public static ResultListFragment newInstance() {
        return new ResultListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.result_list_fragment, container, false);
        getActivity().setTitle("Search results");

        errorText = root.findViewById(R.id.error);
        errorIcon = root.findViewById(R.id.icon);
        listView = root.findViewById(R.id.result_list);

        switch (status) {
            case 404:
                errorText.setVisibility(View.VISIBLE);
                errorIcon.setVisibility(View.VISIBLE);
                break;
            case 201:
                adapter = new ResultListAdapter(getActivity(), users);
                listView.setAdapter(adapter);
                break;
            default:
                //DO NOTHING
                break;
        }
        return root;
    }

    public void getSearchResult(int statusCode, ArrayList<User> users) {
        this.status = statusCode;
        if (users != null) {
            this.users = users;
        }
    }
}

