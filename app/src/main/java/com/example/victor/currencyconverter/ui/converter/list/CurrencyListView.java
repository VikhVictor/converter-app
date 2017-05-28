package com.example.victor.currencyconverter.ui.converter.list;

import com.example.victor.currencyconverter.ui.base.BaseView;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;

import java.util.ArrayList;

/**
 * Created by victor on 27.05.17.
 */

public interface CurrencyListView extends BaseView<CurrencyListPresenter> {

    void onDataLoaded(ArrayList<CurrencyModel> data);

    void onCurrencySelected(int id);
}
