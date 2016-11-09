package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class KYCMedia {

    private String id;
    private String customer_number;
    private String type;
    private String url;
    private String date;
    private String relates_to_kyc_document_id;  //kyc_document_id
    private String relates_to_kyc_check_id; //kyc_check_id

    public KYCMedia(String id, String customer_number, String type, String url, String date, String relates_to_kyc_document_id, String relates_to_kyc_check_id) {
        this.id = id;
        this.customer_number = customer_number;
        this.type = type;
        this.url = url;
        this.date = date;
        this.relates_to_kyc_document_id = relates_to_kyc_document_id;
        this.relates_to_kyc_check_id = relates_to_kyc_check_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRelates_to_kyc_document_id() {
        return relates_to_kyc_document_id;
    }

    public void setRelates_to_kyc_document_id(String relates_to_kyc_document_id) {
        this.relates_to_kyc_document_id = relates_to_kyc_document_id;
    }

    public String getRelates_to_kyc_check_id() {
        return relates_to_kyc_check_id;
    }

    public void setRelates_to_kyc_check_id(String relates_to_kyc_check_id) {
        this.relates_to_kyc_check_id = relates_to_kyc_check_id;
    }
}
