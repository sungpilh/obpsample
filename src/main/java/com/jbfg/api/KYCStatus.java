package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class KYCStatus {
    private String customer_number;
    private boolean ok;
    private String date; //2013-01-22T00:08:00Z

    public KYCStatus(String customer_number, boolean ok, String date) {
        this.customer_number = customer_number;
        this.ok = ok;
        this.date = date;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
