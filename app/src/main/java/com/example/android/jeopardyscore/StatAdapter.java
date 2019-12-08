package com.example.android.jeopardyscore;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StatAdapter extends ArrayAdapter<Stat> {

    private static final String LOG_TAG = StatAdapter.class.getSimpleName();
    private int mColorResourceId;

    public StatAdapter(Activity context, ArrayList<Stat> stats) {
        super(context, 0, stats);
        //mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Stat currentStat = getItem(position);

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView statName = listItemView.findViewById(R.id.stat_name);
        TextView statValue = listItemView.findViewById((R.id.stat_value));

        statName.setText(currentStat.getStatName());
        statValue.setText(currentStat.getStatValue());

        //View textContainer = listItemView.findViewById(R.id.text_container);
        //int color = ContextCompat.getColor(getContext(),mColorResourceId);
        //textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
