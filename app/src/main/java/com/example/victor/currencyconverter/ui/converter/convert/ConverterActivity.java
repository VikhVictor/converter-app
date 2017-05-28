package com.example.victor.currencyconverter.ui.converter.convert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.ui.base.Injection;
import com.example.victor.currencyconverter.ui.converter.list.CurrencyListActivity;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;

/**
 * Created by victor on 24.05.17.
 */

public class ConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        attachConvertFragment();
    }

    private void attachConvertFragment() {
        ConvertFragment fragment = ConvertFragment.newInstance();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container, fragment, ConvertFragment.TAG);
        transaction.commit();

        new ConvertPresenter(fragment,
                Injection.provideTasksRepository(getApplicationContext()),
                Injection.providePreferencesUtils(getApplicationContext()));
    }

}
