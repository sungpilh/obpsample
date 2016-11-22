package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class KYCDocument {
    private String customer_number;
    private String type;
    private String number;
    private String issue_date; //format: 2013-01-22T00:08:00Z
    private String issue_place;
    private String expiry_date; //format: 2013-01-22T00:08:00Z

    public KYCDocument(String customer_number, String type, String number, String issue_date, String issue_place, String expiry_date) {
        this.customer_number = customer_number;
        this.type = type;
        this.number = number;
        this.issue_date = issue_date;
        this.issue_place = issue_place;
        this.expiry_date = expiry_date;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getIssue_place() {
        return issue_place;
    }

    public void setIssue_place(String issue_place) {
        this.issue_place = issue_place;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }
}
