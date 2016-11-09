package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class KYCCheck {

    private String id;
    private String customer_name;
    private String date;  //format : 2013-01-22T00:08:00Z
    private String how;
    private String staff_user_id;
    private String staff_name;
    private boolean satisfied;
    private String comments;

    public KYCCheck(String id, String customer_name, String date, String how, String staff_user_id, String staff_name, boolean satisfied, String comments) {
        this.id = id;
        this.customer_name = customer_name;
        this.date = date;
        this.how = how;
        this.staff_user_id = staff_user_id;
        this.staff_name = staff_name;
        this.satisfied = satisfied;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public String getStaff_user_id() {
        return staff_user_id;
    }

    public void setStaff_user_id(String staff_user_id) {
        this.staff_user_id = staff_user_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
