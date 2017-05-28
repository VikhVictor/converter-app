package com.example.victor.currencyconverter.ui.converter.convert;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.ui.converter.list.CurrencyListActivity;

/**
 * Created by victor on 27.05.17.
 */

public class ConvertFragment extends Fragment implements ConvertView, View.OnClickListener {

    public static final String TAG = ConvertFragment.class.getSimpleName();

    private ConvertPresenter mPresenter;

    private TextView mTVCurrencyFrom, mTVCurrencyTo, mTVResult;
    private EditText mETInput;
    private Button mBtnConvert;
    private ImageView mBtnSwap;

    private ConverterActivity mActivity;

    public static ConvertFragment newInstance() {
        ConvertFragment fragment = new ConvertFragment();
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            mPresenter.onActivityResult(requestCode, data);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (ConverterActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_convert, container, false);

        mTVCurrencyFrom = (TextView) view.findViewById(R.id.tv_currency_from);
        mTVCurrencyTo = (TextView) view.findViewById(R.id.tv_currency_to);

        mTVCurrencyFrom.setOnClickListener(this);
        mTVCurrencyTo.setOnClickListener(this);
        mBtnSwap = (ImageView) view.findViewById(R.id.iv_swap);
        mBtnSwap.setOnClickListener(this);

        mTVResult = (TextView) view.findViewById(R.id.tv_result);

        mETInput = (EditText) view.findViewById(R.id.et_input);
        mBtnConvert = (Button) view.findViewById(R.id.btn_convent);
        mBtnConvert.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onStart();
    }

    @Override
    public void openCurrencyList(int requestCode) {
        Intent intent = new Intent(getContext(), CurrencyListActivity.class);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void setCurrencies(String from, String to) {
        mTVCurrencyTo.setText(to);
        mTVCurrencyFrom.setText(from);
        mETInput.setHint(from);
    }

    @Override
    public void setResult(String result) {
        mTVResult.setText(result);
    }

    @Override
    public void onShowError(int res) {
        Snackbar.make(getView(), getString(res), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setPresenter(ConvertPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_currency_from:
                mPresenter.onCurrencyFromClick();
                break;
            case R.id.tv_currency_to:
                mPresenter.onCurrencyToClick();
                break;
            case R.id.btn_convent:
                mPresenter.onConvertClick(mETInput.getText().toString());
                break;
            case R.id.iv_swap:
                mPresenter.onSwapClick(mETInput.getText().toString());
                break;
        }
    }

}
