package com.example.victor.currencyconverter.ui.converter.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.victor.currencyconverter.R;
import com.example.victor.currencyconverter.ui.base.BaseAdapter;
import com.example.victor.currencyconverter.ui.model.local.CurrencyModel;

import java.util.ArrayList;

/**
 * Created by victor on 28.05.17.
 */

public class CurrencyRVAdapter extends BaseAdapter {

    private ArrayList<CurrencyModel> mCurruncies = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_currencies_item, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder baseHolder, int position) {
        if (baseHolder instanceof CurrencyViewHolder) {
            CurrencyModel model = mCurruncies.get(position);
            CurrencyViewHolder holder = (CurrencyViewHolder) baseHolder;
            holder.mTVTitle.setText(model.getName());
            holder.mTVSubtitle.setText(model.getShortName());
        }
    }

    @Override
    public int getItemCount() {
        return mCurruncies.size();
    }

    public void updateData(ArrayList<CurrencyModel> data) {
        this.mCurruncies = data;
        notifyDataSetChanged();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTVTitle, mTVSubtitle;

        public CurrencyViewHolder(View view) {
            super(view);
            mTVTitle = (TextView) view.findViewById(R.id.tv_title);
            mTVSubtitle = (TextView) view.findViewById(R.id.tv_subtitle);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }
}
