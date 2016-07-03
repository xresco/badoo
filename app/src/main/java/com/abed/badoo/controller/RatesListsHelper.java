package com.abed.badoo.controller;

import com.abed.badoo.data.model.Rate;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mindvalley on 03/07/2016.
 */

public class RatesListsHelper {


    public static List<Rate> calculateDifference(List<Rate> list1, List<Rate> list2) {
        List<Rate> listtmp = new LinkedList<>();
        for (Rate rate : list1) {
            if (!list2.contains(rate)) {
                listtmp.add(rate);
            }
        }
        return listtmp;
    }

}
