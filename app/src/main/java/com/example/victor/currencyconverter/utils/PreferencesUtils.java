package com.example.victor.currencyconverter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.victor.currencyconverter.R;

/**
 * Created by victor on 27.05.17.
 */

public class PreferencesUtils {

    private static final String KEY_SETTINGS = "key_settings";

    private static PreferencesUtils mInstance;

    private SharedPreferences mPreferences;
    private Context mContext;

    public PreferencesUtils(Context context) {
        mContext = context;
        mPreferences = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
    }

    public static PreferencesUtils getInstance(Context context) {
        if (mInstance == null) {
            return new PreferencesUtils(context);
        }
        return mInstance;
    }

    public String getString(int resId) {
        return mContext.getString(resId);
    }

    public SharedPreferences getPrefs() {
        return mPreferences;
    }

}
