package com.abed.badoo.ui.Transactions;

import android.content.Context;

import com.abed.badoo.Application;
import com.abed.badoo.data.model.Rate;
import com.abed.badoo.ui.base.BasePresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class TransactionsPresenter extends BasePresenter<TransactionsMvpView> {
    private String TAG = getClass().getName();

    @Inject
    public TransactionsPresenter() {
    }

    @Override
    public void attachView(TransactionsMvpView mvpView) {
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
        getMvpView().showRates(rates);
    }


}
