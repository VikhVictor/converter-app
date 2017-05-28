package com.example.victor.currencyconverter.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.victor.currencyconverter.utils.CurrencyLocalSource;
import com.example.victor.currencyconverter.utils.PreferencesUtils;

/**
 * Created by victor on 27.05.17.
 */

public class Injection {
    public static CurrencyLocalSource provideTasksRepository(@NonNull Context context) {
        return CurrencyLocalSource.getInstance(context);
    }

    public static PreferencesUtils providePreferencesUtils(@NonNull Context context) {
        return PreferencesUtils.getInstance(context);
    }
}
