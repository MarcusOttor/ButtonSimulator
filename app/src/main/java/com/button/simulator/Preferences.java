package com.button.simulator;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String FIRST_TIME = "FIRST_TIME";
    public static final String CLICKS_COUNT = "CLICKS_COUNT";
    public static final String IMAGE_URI = "IMAGE_URI";

    private SharedPreferences preferences;

    public Preferences(Context context) {
        preferences = context.getSharedPreferences(null, Context.MODE_PRIVATE);
    }

    public void setFirstTime(Boolean isFirst) {
        preferences.edit().putBoolean(FIRST_TIME, isFirst).apply();
    }

    public Boolean isFirstTime(Boolean defValue) {
        return preferences.getBoolean(FIRST_TIME, defValue);
    }

    public void addClicks(Integer clicks) {
        preferences.edit().putInt(CLICKS_COUNT, getClicks() + clicks).apply();
    }

    public Integer getClicks() {
        return preferences.getInt(CLICKS_COUNT, 0);
    }

    public String getImageUri() {
        return preferences.getString(IMAGE_URI, "");
    }

    public void setImageUri(String uri) {
        preferences.edit().putString(IMAGE_URI, uri).apply();
    }
}
