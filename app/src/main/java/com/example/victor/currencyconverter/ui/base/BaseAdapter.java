package com.example.victor.currencyconverter.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by victor on 28.05.17.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {

    protected OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
