package com.abed.badoo.ui.Main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.abed.badoo.R;
import com.abed.badoo.data.model.Transaction;
import com.abed.badoo.ui.Transactions.TransactionsActivity;
import com.abed.badoo.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    MainAdapter mainAdapter;

    @BindView(R.id.recylcer_imgs)
    RecyclerView recyclerImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter.attachView(this);
        mMainPresenter.loadData(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerImgs.setLayoutManager(mLayoutManager);
        recyclerImgs.setAdapter(mainAdapter);
        mainAdapter.setClicksListener(new MainAdapter.ViewHolderClicks() {
            @Override
            public void onItemClick(View view, int position,String sku, ArrayList<Transaction> transactions) {
                TransactionsActivity.startActivity(view.getContext(),sku,transactions);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showTransactions(List<Transaction> transactions) {
        mainAdapter.updateList(transactions);
    }

}
