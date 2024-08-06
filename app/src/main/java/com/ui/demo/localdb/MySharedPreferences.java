package com.ui.demo.localdb;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {

    private static MySharedPreferences ourInstance = null;
    private final SharedPreferences savePreferences;
    private final SharedPreferences.Editor editor;

    public static MySharedPreferences getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MySharedPreferences(context);
        }
        return ourInstance;
    }

    // Initialize Share Preference App with context.
    @SuppressLint("CommitPrefEdits")
    private MySharedPreferences(Context context) {
        String PREF_NAME = "UIDemo";
        savePreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = savePreferences.edit();
    }

    public void saveStringValue(String key, String value) {
        editor.putString(key, value);
        editor.apply();
    }

    public void saveBooleanValue(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void saveInteger(String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    public void saveDouble(String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        return savePreferences.getString(key, "");
    }

    public int getIntegerValue(String key) {
        return savePreferences.getInt(key, 0);
    }

    public boolean getBooleanValue(String key) {
        return savePreferences.getBoolean(key, false);
    }

    public float getDoubleValue(String key) {
        return savePreferences.getFloat(key, 0f);
    }
}
