package com.abed.badoo.ui.Main;

import android.content.Context;
import android.util.Log;

import com.abed.badoo.Application;
import com.abed.badoo.data.model.Rate;
import com.abed.badoo.data.model.Transaction;
import com.abed.badoo.ui.base.BasePresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainMvpView> {
    private String TAG = getClass().getName();

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void loadData(Context context) {
        String content = Application.get(context).getComponent().dataManager().getLocalFilesHelper().getFileContent(context, "rates.json");
        Gson gson = new Gson();
        Type ratesListType = new TypeToken<List<Rate>>() {}.getType();
        List<Rate> rates = gson.fromJson(content, ratesListType);
        Log.d("content", rates.size() + "");


        content = Application.get(context).getComponent().dataManager().getLocalFilesHelper().getFileContent(context, "transactions.json");
        Type transactionsListType = new TypeToken<List<Transaction>>() {}.getType();
        List<Transaction> transactions = gson.fromJson(content, transactionsListType);
        Log.d("content", transactions.size() + "");

        getMvpView().showTransactions(transactions);
    }


}
