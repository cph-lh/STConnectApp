package com.example.stconnectapp.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stconnectapp.Model.User;
import com.example.stconnectapp.R;

import java.util.ArrayList;

public class ResultListAdapter extends BaseAdapter {

    private ArrayList<User> users;
    private Context context;

    public ResultListAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.result_list_layout, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        Log.d("ADAPTER", "POSITION: " + position);
        vh.bindData(users.get(position));

        return convertView;
    }

    private class ViewHolder {
        private User listUser;
        private TextView userInfo, userName;

        public ViewHolder(View view) {
            userName = view.findViewById(R.id.user_name);
            userInfo = view.findViewById(R.id.user_info);
        }

        public void bindData(User user) {
            listUser = user;
            Log.d("ADAPTER", "USER: " + user.getName());
            userName.setText(user.getName());
            if (!user.getSkills().isEmpty()) {
                String skills = "";
                Log.d("ADAPTER", "Skill: " + user.getSkills().get(0).getName());
                for (int i = 0; i < user.getSkills().size(); i++) {

                    skills += user.getSkills().get(i).name;
                    if (user.getSkills().size() - 1 != i) {
                        skills += ", ";
                    }
                }
                userInfo.setText(skills);
            }
        }
    }
}
