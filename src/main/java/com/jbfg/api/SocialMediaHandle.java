package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class SocialMediaHandle {
    private String customer_number;
    private String type;
    private String handle;
    private String date_added;  //2013-01-22T00:08:00Z
    private String date_activated;  //2013-01-22T00:08:00Z

    public SocialMediaHandle(String customer_number, String type, String handle, String date_added, String date_activated) {
        this.customer_number = customer_number;
        this.type = type;
        this.handle = handle;
        this.date_added = date_added;
        this.date_activated = date_activated;
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

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public String getDate_activated() {
        return date_activated;
    }

    public void setDate_activated(String date_activated) {
        this.date_activated = date_activated;
    }
}
