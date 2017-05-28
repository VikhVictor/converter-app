package com.example.victor.currencyconverter.ui.converter.convert;

import android.content.Intent;
import android.content.SharedPreferences;

import com.example.victor.currencyconverter.ui.converter.list.CurrencyListActivity;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;
import com.example.victor.currencyconverter.utils.CurrencyLocalSource;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.utils.PreferencesUtils;

/**
 * Created by victor on 24.05.17.
 */

public class ConvertPresenter {

    private static final int REQUEST_CODE_CURRENCY_FROM = 11;
    private static final int REQUEST_CODE_CURRENCY_TO = 12;

    private ConvertView mView;
    private CurrencyLocalSource mDBHelper;

    private CurrencyModel mCurrencyFrom;
    private CurrencyModel mCurrencyTo;

    private PreferencesUtils mPrefUtils;

    public ConvertPresenter(ConvertView view, CurrencyLocalSource databaseHelper, PreferencesUtils preferencesUtils) {
        this.mView = view;
        this.mDBHelper = databaseHelper;
        this.mPrefUtils = preferencesUtils;

        mView.setPresenter(this);
    }


    public void onConvertClick(String input) {

        if (input.length() == 0) {
            mView.onShowError(R.string.activity_converter_amount_not_entered);
            return;
        }

        if (mCurrencyFrom == null || mCurrencyTo == null) {
            mView.onShowError(R.string.activity_converter_currency_not_selected);
            return;
        }

        mView.setResult(convertValue(input));
    }

    private String convertValue(String source) {
        double value = Double.valueOf(source);

        double baseValueFrom = mCurrencyFrom.getValue() / mCurrencyFrom.getNominal();
        double baseValueTo = mCurrencyTo.getValue() / mCurrencyTo.getNominal();

        double result = value * baseValueFrom / baseValueTo;

        return new DecimalFormat("#.####").format(result);
    }

    public void onStart() {

        int fromId = mPrefUtils.getPrefs().getInt(mPrefUtils.getString(R.string.key_save_currency_id_from), 0);
        int toId = mPrefUtils.getPrefs().getInt(mPrefUtils.getString(R.string.key_save_currency_id_to), 0);

        mCurrencyFrom = mDBHelper.getCurrencyById(fromId);
        mCurrencyTo = mDBHelper.getCurrencyById(toId);

        if (mCurrencyFrom == null || mCurrencyTo == null) {
            mView.onShowError(R.string.activity_converter_none_currencies);
            return;
        }

        mView.setCurrencies(mCurrencyFrom.getShortName(), mCurrencyTo.getShortName());
    }

    public void onCurrencyFromClick() {
        mView.openCurrencyList(REQUEST_CODE_CURRENCY_FROM);
    }

    public void onCurrencyToClick() {
        mView.openCurrencyList(REQUEST_CODE_CURRENCY_TO);
    }

    public void onActivityResult(int requestCode, Intent data) {
        if (data == null) {
            return;
        }

        int id = data.getIntExtra(CurrencyListActivity.KEY_SELECTED_CURRENCY_ID, 0);
        //CurrencyModel model = mDBHelper.getCurrencyById(id);

        if (requestCode == REQUEST_CODE_CURRENCY_FROM) {
            mPrefUtils.getPrefs()
                    .edit()
                    .putInt(mPrefUtils.getString(R.string.key_save_currency_id_from), id)
                    .commit();
        } else if (requestCode == REQUEST_CODE_CURRENCY_TO) {
            mPrefUtils.getPrefs()
                    .edit()
                    .putInt(mPrefUtils.getString(R.string.key_save_currency_id_to), id)
                    .commit();
        }
    }

    public void onSwapClick(String input) {
        if (mCurrencyFrom == null || mCurrencyTo == null) {
            mView.onShowError(R.string.activity_converter_currency_not_selected);
            return;
        }

        CurrencyModel tmp = mCurrencyFrom;
        mCurrencyFrom = new CurrencyModel(mCurrencyTo);
        mCurrencyTo = tmp;
        if (input.length() > 0) {
            mView.setResult(convertValue(input));
        }
        mView.setCurrencies(mCurrencyFrom.getShortName(), mCurrencyTo.getShortName());
    }
}
