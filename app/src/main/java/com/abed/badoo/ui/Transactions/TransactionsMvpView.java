package com.abed.badoo.ui.Transactions;

import com.abed.badoo.data.model.Rate;
import com.abed.badoo.ui.base.MvpView;

import java.util.List;

public interface TransactionsMvpView extends MvpView {

    void showRates(List<Rate> rates);

}
