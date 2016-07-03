package com.abed.badoo.controller;

import com.abed.badoo.data.model.Rate;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by mindvalley on 03/07/2016.
 */

public class AmountConverter {


    @Inject
    public AmountConverter() {
    }

    public float convertAmount(String from, String to, float amount, List<Rate> rates) {

        //Case1: To == From
        if (from.equals(to))
            return amount;

        //Case2: There is no conversion rate to the currency provided
        List<Rate> ratesConvertingTo = getAllRatesConvertingTo(to, rates);
        if (ratesConvertingTo.size() == 0)
            return -1;

        //Case3: There is a direct way to convert the amount
        Rate conversionRate = getRateConvertingFrom(from, ratesConvertingTo);
        if (conversionRate != null)
            return amount * conversionRate.getRate();

        //Case3: There is no direct way to convert the amount. So we have to search for another way using multiple conversions
        float cheapestConversionRate = -1;
        for (Rate middleLayerConversion : ratesConvertingTo) {
            float rate = convertAmount(from, middleLayerConversion.getFrom(), 1, RatesListsHelper.calculateDifference(rates, ratesConvertingTo));
            if (rate > 0) {
                if (cheapestConversionRate == -1 || cheapestConversionRate > rate * middleLayerConversion.getRate())
                    cheapestConversionRate = rate * middleLayerConversion.getRate();
            }
        }
        return cheapestConversionRate == -1 ? -1 : cheapestConversionRate * amount;
    }


    /**
     * Returns all the rates the converts to a specified currency
     *
     * @param to
     * @param rates
     * @return
     */
    public List<Rate> getAllRatesConvertingTo(String to, List<Rate> rates) {
        List<Rate> ratesTo = new LinkedList<>();
        for (Rate rate : rates) {
            if (rate.getTo().equals(to)) {
                ratesTo.add(rate);
            }
        }

        return ratesTo;
    }


    /**
     * Returns all a rate the converts from a specified currency
     *
     * @param from
     * @param rates
     * @return
     */

    public Rate getRateConvertingFrom(String from, List<Rate> rates) {
        for (Rate rate : rates) {
            if (rate.getFrom().equals(from))
                return rate;
        }
        return null;
    }
}
