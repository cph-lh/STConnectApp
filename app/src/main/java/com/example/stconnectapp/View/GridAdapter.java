package com.example.stconnectapp.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stconnectapp.R;

public class GridAdapter extends BaseAdapter{

    private final Context mContext;
    private final String[] gridStrings;
    private final int[] gridImages;

    public GridAdapter(Context context, String[] gridStrings, int[] gridImages) {
        this.mContext = context;
        this.gridImages = gridImages;
        this.gridStrings = gridStrings;
    }

    @Override
    public int getCount() {
        return gridStrings.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.gridview_layout, null);
        }

        final ImageView imageView = convertView.findViewById(R.id.grid_icon);
        final TextView textView = convertView.findViewById(R.id.grid_text);

        textView.setText(gridStrings[position]);
        imageView.setImageResource(gridImages[position]);

        return convertView;
    }
}
