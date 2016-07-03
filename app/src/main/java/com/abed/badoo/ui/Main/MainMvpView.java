package com.abed.badoo.ui.Main;

import com.abed.badoo.data.model.Transaction;
import com.abed.badoo.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showTransactions(List<Transaction> transactions);

}
