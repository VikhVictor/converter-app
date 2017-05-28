package com.example.victor.currencyconverter.ui.converter.list;

import android.content.SharedPreferences;

import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.ui.base.BasePresenter;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;
import com.example.victor.currencyconverter.utils.CurrencyLocalSource;
import com.example.victor.currencyconverter.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 27.05.17.
 */

public class CurrencyListPresenter {

    private List<CurrencyModel> mCurrencyList = new ArrayList<>();
    private CurrencyListView mView;

    private CurrencyLocalSource mDBHelper;
    private PreferencesUtils mPreferences;

    public CurrencyListPresenter(CurrencyListView view, CurrencyLocalSource databaseHelper, PreferencesUtils preferencesUtils) {
        this.mView = view;
        this.mDBHelper = databaseHelper;
        this.mPreferences = preferencesUtils;

        mView.setPresenter(this);
    }

    public void onStart() {
        mCurrencyList = mDBHelper.getCurrencyList();

        if (mCurrencyList.size() == 0) {
            mView.onShowError(R.string.activity_converter_none_currencies);
            return;
        }

        mView.onDataLoaded(new ArrayList<>(mCurrencyList));
    }

    public void onItemClick(int position) {
        if (position < mCurrencyList.size()) {
            mView.onCurrencySelected(mCurrencyList.get(position).getId());
        }
    }
}
