package com.example.victor.currencyconverter.ui.base;

import com.example.victor.currencyconverter.ui.converter.convert.ConvertPresenter;

/**
 * Created by victor on 24.05.17.
 */

public interface BaseView<T> {
    void onShowError(int res);

    void setPresenter(T presenter);
}
