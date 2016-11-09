package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class CustomerMessage {
    private String message;
    private String from_department;
    private String from_person;

    public CustomerMessage(String message, String from_department, String from_person) {
        this.message = message;
        this.from_department = from_department;
        this.from_person = from_person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom_department() {
        return from_department;
    }

    public void setFrom_department(String from_department) {
        this.from_department = from_department;
    }

    public String getFrom_person() {
        return from_person;
    }

    public void setFrom_person(String from_person) {
        this.from_person = from_person;
    }
}
