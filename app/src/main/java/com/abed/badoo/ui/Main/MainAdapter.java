package com.abed.badoo.ui.Main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abed.badoo.R;
import com.abed.badoo.controller.TransactionProcessor;
import com.abed.badoo.data.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ViewHolderClicks clicksListener;
    private Map<String, ArrayList<Transaction>> transactionsMap;
    private ArrayList<String> SKUs;

    @Inject
    TransactionProcessor transactionProcessor;


    @Inject
    public MainAdapter() {
    }

    public void setClicksListener(ViewHolderClicks clicksListener) {
        this.clicksListener = clicksListener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_subtitle, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (transactionsMap == null || transactionsMap.size() == 0) {
            return;
        }


        holder.setSku(SKUs.get(position),
                transactionsMap.get(SKUs.get(position)));
    }

    @Override
    public int getItemCount() {
        if (transactionsMap == null) {
            return 0;
        }
        return SKUs.size();
    }

    public void updateList(List<Transaction> transactions) {
        transactionsMap = transactionProcessor.groupTransactions(transactions);
        SKUs = new ArrayList<>(transactionsMap.keySet());
        notifyDataSetChanged();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        ViewHolderClicks clicksListener;
        @BindView(R.id.txt_title)
        TextView txt_sku_name;

        @BindView(R.id.txt_subtitle)
        TextView txt_transactions_Count;

        private ArrayList<Transaction> transactions;
        private String sku;

        public CustomViewHolder(final View view, final ViewHolderClicks clicksListener) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicksListener.onItemClick(view, getLayoutPosition(), sku, transactions);
                }
            });
            this.clicksListener = clicksListener;
        }


        public void setSku(String sku, ArrayList<Transaction> transactions) {
            this.transactions = transactions;
            this.sku = sku;
            txt_sku_name.setText(sku);
            txt_transactions_Count.setText(transactions.size() + " transactions");
        }


    }


    public interface ViewHolderClicks {
        void onItemClick(View view, int position, String sku, ArrayList<Transaction> transactions);
    }

}
