
package com.abed.badoo.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {
    private String amount;
    private String sku;
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public String getSku() {
        return sku;
    }

    public float getAmount() {
        try {
            return Float.valueOf(amount);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: a report to the servers should happen here telling there's an issue in the data provided
        }
        return -1;
    }


    protected Transaction(Parcel in) {
        amount = in.readString();
        sku = in.readString();
        currency = in.readString();


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(amount);
        dest.writeString(sku);
        dest.writeString(currency);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
