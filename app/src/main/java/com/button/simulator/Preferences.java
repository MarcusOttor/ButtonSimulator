package com.button.simulator;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String FIRST_TIME = "FIRST_TIME";

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
}
