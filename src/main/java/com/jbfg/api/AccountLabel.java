package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class AccountLabel {

    private String id;  //account_id
    private String label;
    private String bank_id;

    public AccountLabel(String id, String label, String bank_id) {
        this.id = id;
        this.label = label;
        this.bank_id = bank_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }
}
