package com.example.victor.currencyconverter.ui.converter.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.ui.base.Injection;
import com.example.victor.currencyconverter.ui.converter.convert.ConvertFragment;
import com.example.victor.currencyconverter.ui.converter.convert.ConvertPresenter;

/**
 * Created by victor on 28.05.17.
 */

public class CurrencyListActivity extends AppCompatActivity implements CurrencyListFragment.OnCurrencySelectedListener {

    public static final String KEY_SELECTED_CURRENCY_ID = "selected_currency";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_list);

        CurrencyListFragment fragment = CurrencyListFragment.newInstance("");

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_container, fragment, CurrencyListFragment.TAG);
        transaction.commit();

        new CurrencyListPresenter(fragment,
                Injection.provideTasksRepository(getApplicationContext()),
                Injection.providePreferencesUtils(getApplicationContext()));
    }

    @Override
    public void onCurrencySelected(int id, String tag) {
        Intent result = new Intent();
        result.putExtra(KEY_SELECTED_CURRENCY_ID, id);
        setResult(RESULT_OK, result);
        finish();
    }
}
