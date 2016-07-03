
package com.abed.badoo.data.model;

public class Rate {
    private String from;
    private String rate;
    private String to;


    public String getFrom() {
        return from;
    }

    public float getRate() {
        try {
            return Float.valueOf(rate);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: a report to the servers should happen here telling there's an issue in the data provided
        }
        return -1;
    }

    public String getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Rate) {
            Rate object = (Rate) o;
            return from.equals(object.getFrom()) && to.equals(object.getTo());
        }
        return false;
    }
}
