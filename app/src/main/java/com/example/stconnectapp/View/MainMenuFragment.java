package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.stconnectapp.Model.Helper;
import com.example.stconnectapp.R;

public class MainMenuFragment extends Fragment {

    private View root;
    private GridView grid;

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    String[] gridStrings = {"Profile", "Change password", "Search"};
    int[] gridImages = {R.drawable.ic_person, R.drawable.ic_edit, R.drawable.ic_search};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        root = inflater.inflate(R.layout.main_menu_fragment, container, false);

        grid = root.findViewById(R.id.gridview);
        grid.setAdapter(new GridAdapter(Helper.getContext(), gridStrings, gridImages));

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        //Profile fragment
                        break;
                    case 1:
                        ChangePasswordFragment passwordFragment = ChangePasswordFragment.newInstance();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, passwordFragment).addToBackStack(null).commit();
                        break;
                    case 2:
                        SearchFragment searchFragment = SearchFragment.newInstance();
                        getFragmentManager().beginTransaction().replace(R.id.fragment_container, searchFragment).addToBackStack(null).commit();
                        break;
                }
            }
        });
        return root;
    }
}
