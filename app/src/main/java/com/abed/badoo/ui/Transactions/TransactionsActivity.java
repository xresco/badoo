package com.abed.badoo.ui.Transactions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.abed.badoo.R;
import com.abed.badoo.controller.AmountConverter;
import com.abed.badoo.data.model.Rate;
import com.abed.badoo.data.model.Transaction;
import com.abed.badoo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionsActivity extends BaseActivity implements TransactionsMvpView {

    @Inject
    TransactionsPresenter mTransactionsPresenter;
    @Inject
    TransactionsAdapter transactionsAdapter;

    @BindView(R.id.recylcer_imgs)
    RecyclerView recyclerImgs;

    @BindView(R.id.txt_transactions_total)
    TextView txtTransactionsTotal;

    @Inject
    AmountConverter amountConverter;

    private String sku;
    private List<Transaction> transactions;

    private final static String EXTRA_TR = "EXTRA_TR";
    private final static String EXTRA_SKU = "EXTRA_SKU";

    public static void startActivity(Context context, String sku, ArrayList<Transaction> transactions) {
        Intent intent = new Intent(context, TransactionsActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_TR, transactions);
        intent.putExtra(EXTRA_SKU, sku);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        transactions = getIntent().getParcelableArrayListExtra(EXTRA_TR);
        sku = getIntent().getStringExtra(EXTRA_SKU);
        setContentView(R.layout.activity_transactions);
        ButterKnife.bind(this);
        mTransactionsPresenter.attachView(this);
        mTransactionsPresenter.loadData(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerImgs.setLayoutManager(mLayoutManager);
        recyclerImgs.setAdapter(transactionsAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTransactionsPresenter.detachView();
    }

    @Override
    public void showRates(List<Rate> rates) {
        transactionsAdapter.updateList(transactions, rates);

        float total = 0;
        for (Transaction transaction : transactions) {
            total += amountConverter.convertAmount(transaction.getCurrency(), "GBP", transaction.getAmount(), rates);
        }
        txtTransactionsTotal.setText("Total: GBP " + total);
    }

}
