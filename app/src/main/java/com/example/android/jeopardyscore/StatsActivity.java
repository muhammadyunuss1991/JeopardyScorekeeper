package com.example.android.jeopardyscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat_list);

        String totalCorrect = String.valueOf(MainActivity.winningsCountJ + MainActivity.winningsCountDJ);

        //List of Stats
        ArrayList<Stat> stats = new ArrayList<>();
        stats.add(new Stat("Total Winnings", MainActivity.getWinningsAmount()));
        stats.add(new Stat("Total Correct in Jeopardy Round", MainActivity.getWinningsCountJ()));
        stats.add(new Stat("Total Correct in Double Jeopardy Round", MainActivity.getWinningsCountDJ()));
        stats.add(new Stat("Total Correct", totalCorrect));
        stats.add(new Stat("Final Jeopardy Correct", MainActivity.getFinalJeopardyResult()));
        stats.add(new Stat("Percentage Correct in Jeopardy Round", MainActivity.getWinningsCountJPercent()));
        stats.add(new Stat("Percentage Correct in Double Jeopardy Round", MainActivity.getWinningsCountDJPercent()));
        stats.add(new Stat("Total Percentage Correct", MainActivity.getTotalWinningsCountPercentage()));

        StatAdapter statAdapter = new StatAdapter(this, stats);

        ListView listView =  findViewById(R.id.list);

        listView.setAdapter(statAdapter);
    }
}
