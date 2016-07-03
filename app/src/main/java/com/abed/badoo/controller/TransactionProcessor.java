package com.abed.badoo.controller;

import com.abed.badoo.data.model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by mindvalley on 03/07/2016.
 */

public class TransactionProcessor {

    @Inject
    public TransactionProcessor() {
    }

    public Map<String, ArrayList<Transaction>> groupTransactions(List<Transaction> transactions) {
        Map<String, ArrayList<Transaction>> transactionsMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (!transactionsMap.containsKey(transaction.getSku())) {
                transactionsMap.put(transaction.getSku(), new ArrayList<Transaction>());
            }
            transactionsMap.get(transaction.getSku()).add(transaction);
        }
        return transactionsMap;
    }
}
