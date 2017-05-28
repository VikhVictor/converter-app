package com.example.victor.currencyconverter.ui.converter.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.ui.base.BaseAdapter;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;

/**
 * Created by victor on 27.05.17.
 */

public class CurrencyListFragment extends Fragment implements CurrencyListView, BaseAdapter.OnItemClickListener {

    public static final String TAG = CurrencyListFragment.class.getSimpleName();

    public static final String KEY_CURRENCIES = "currencies";
    private static final String KEY_CURRENCY_NAMES = "curNames";
    private static final String KEY_SELECT_TAG = "tag";

    private CurrencyListPresenter mPresenter;
    private String mTag;

    private RecyclerView mRVCurrencies;
    private CurrencyRVAdapter mAdapter;

    private OnCurrencySelectedListener mSelectListener;

    public static CurrencyListFragment newInstance(String tag) {
        CurrencyListFragment fragment = new CurrencyListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_SELECT_TAG, tag);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            mTag = getArguments().getString(KEY_SELECT_TAG);
        }
        mSelectListener = (OnCurrencySelectedListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_currency_list, container, false);
        mRVCurrencies = (RecyclerView) view.findViewById(R.id.lv_result);
        mRVCurrencies.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new CurrencyRVAdapter();
        mAdapter.setOnItemClickListener(this);
        mRVCurrencies.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }


    @Override
    public void onShowError(int res) {

    }

    @Override
    public void setPresenter(CurrencyListPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDataLoaded(ArrayList<CurrencyModel> data) {
        mAdapter.updateData(data);
    }

    @Override
    public void onCurrencySelected(int id) {
        mSelectListener.onCurrencySelected(id, mTag);
    }

    @Override
    public void onItemClick(View view, int position) {
        mPresenter.onItemClick(position);
    }


    public interface OnCurrencySelectedListener {
        void onCurrencySelected(int id, String tag);
    }
}
