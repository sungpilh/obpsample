package com.jbfg.api;

import java.util.HashMap;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class TransactionRequest {

    private To to;
    private Balance value;
    private String description;

    protected class To{
        private String bank_id;
        private String account_id;

        public To(String bank_id, String account_id) {
            this.bank_id = bank_id;
            this.account_id = account_id;
        }

        public String getBank_id() {
            return bank_id;
        }

        public void setBank_id(String bank_id) {
            this.bank_id = bank_id;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }
    }

    public TransactionRequest(String bank_id, String account_id, Balance value, String description) {
        this.to = new To(bank_id, account_id);
        this.value = value;
        this.description = description;
    }

    public To getTo() {
        return to;
    }

    public void setTo(To to) {
        this.to = to;
    }

    public Balance getValue() {
        return value;
    }

    public void setValue(Balance value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
