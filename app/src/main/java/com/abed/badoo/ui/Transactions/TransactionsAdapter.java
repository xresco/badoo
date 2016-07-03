package com.abed.badoo.ui.Transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abed.badoo.R;
import com.abed.badoo.controller.AmountConverter;
import com.abed.badoo.data.model.Rate;
import com.abed.badoo.data.model.Transaction;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.CustomViewHolder> {

    private List<Rate> rates;
    private List<Transaction> transactions;

    @Inject
    AmountConverter amountConverter;


    @Inject
    public TransactionsAdapter() {
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_subtitle, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (transactions == null || transactions.size() == 0) {
            return;
        }

        Transaction tr = transactions.get(position);
        holder.setTransaction(tr, amountConverter.convertAmount(tr.getCurrency(), "GBP", tr.getAmount(), rates));
    }

    @Override
    public int getItemCount() {
        if (transactions == null) {
            return 0;
        }
        return transactions.size();
    }

    public void updateList(List<Transaction> transactions, List<Rate> rates) {
        this.transactions = transactions;
        this.rates = rates;
        notifyDataSetChanged();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView txt_amount;

        @BindView(R.id.txt_subtitle)
        TextView txt_converted_amount;

        public CustomViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
        }


        public void setTransaction(Transaction transaction, float converted_amont) {
            txt_amount.setText(transaction.getCurrency() + " " + transaction.getAmount());
            txt_converted_amount.setText("GBP " + converted_amont);
        }


    }

}
