package com.example.android.jeopardyscore;

public class Stat {

    private String mStatName;
    private String mStatValue;

    public Stat(String statName, String statValue) {
        mStatName = statName;
        mStatValue = statValue;
    }

    public String getStatName(){
        return mStatName;
    }

    public String getStatValue() {
        return mStatValue;
    }
}
