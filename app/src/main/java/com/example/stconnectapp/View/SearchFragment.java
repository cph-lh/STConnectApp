package com.example.stconnectapp.View;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.stconnectapp.Model.SearchHandler;
import com.example.stconnectapp.Model.Search;
import com.example.stconnectapp.Model.Skill;
import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.R;

import org.json.JSONException;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchHandler handler;
    private View root;
    private TextView name, email, education, experience, skills;
    private Button search;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.search_fragment, container, false);

        getActivity().setTitle("Search");
        handler = new SearchHandler(this);

        name = root.findViewById(R.id.search_name);
        email = root.findViewById(R.id.search_email);
        education = root.findViewById(R.id.search_education);
        experience = root.findViewById(R.id.search_experience);
        skills = root.findViewById(R.id.search_skills);
        search = root.findViewById(R.id.search_button);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        return root;
    }

    public void search() {
        Search search = new Search();
        if (!TextUtils.isEmpty(name.getText())) {
            search.setName(name.getText().toString());
        }
        if (!TextUtils.isEmpty(email.getText())) {
            search.setEmail(email.getText().toString());
        }
        if (!TextUtils.isEmpty(education.getText())) {
            search.setEducation(education.getText().toString());
        }
        if (!TextUtils.isEmpty(experience.getText())) {
            search.setExperience(experience.getText().toString());
        }
        if (!TextUtils.isEmpty(skills.getText())) {
            Skill skill = new Skill();
            skill.setName(skills.getText().toString());
            ArrayList<Skill> skills = new ArrayList<>();
            skills.add(skill);
            search.setSkill(skills);
        }
        handler.searchFilter(search);
    }

    public void searchResult(int statusCode, ArrayList<User> users) {
        if (statusCode != 0) {
            ResultListFragment results = ResultListFragment.newInstance();
            results.getSearchResult(statusCode, users);
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, results).addToBackStack(null).commit();
        }
    }
}
