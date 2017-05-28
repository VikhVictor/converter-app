package com.example.victor.currencyconverter.ui.converter.convert;

import com.example.victor.currencyconverter.ui.base.BaseView;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;

import java.util.ArrayList;

/**
 * Created by victor on 24.05.17.
 */

public interface ConvertView extends BaseView<ConvertPresenter> {
    void openCurrencyList(int requestCode);

    void setCurrencies(String from, String to);

    void setResult(String result);
}
