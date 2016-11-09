package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class Image {

    private String URL;
    private String date;

    public Image(String URL, String date) {
        this.URL = URL;
        this.date = date;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
