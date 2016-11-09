package com.jbfg.api;

/**
 * Created by Sungpil Hyun on 2016. 11. 4..
 */
public class Customer {

    private String user_id;
    private String customer_number;
    private String legal_name;
    private String mobile_hone_number;
    private String email;
    private String date_of_birth;
    private String relationship_status; //Single
    private int dependants; //1
    private String[] dob_of_dependants; //["2013-01-22T00:08:00Z"]
    private String highest_education_attained; //Bachelor’s Degree",
    private String employment_status; //Employed
    private boolean kyc_status; //true,
    private String last_ok_date; //2013-01-22T00:08:00Z
    private Image face_image;

    public Customer(String user_id, String customer_number, String legal_name, String mobile_hone_number, String email, String date_of_birth, String relationship_status, int dependants, String[] dob_of_dependants, String highest_education_attained, String employment_status, boolean kyc_status, String last_ok_date, Image face_image) {
        this.user_id = user_id;
        this.customer_number = customer_number;
        this.legal_name = legal_name;
        this.mobile_hone_number = mobile_hone_number;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.relationship_status = relationship_status;
        this.dependants = dependants;
        this.dob_of_dependants = dob_of_dependants;
        this.highest_education_attained = highest_education_attained;
        this.employment_status = employment_status;
        this.kyc_status = kyc_status;
        this.last_ok_date = last_ok_date;
        this.face_image = face_image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getLegal_name() {
        return legal_name;
    }

    public void setLegal_name(String legal_name) {
        this.legal_name = legal_name;
    }

    public String getMobile_hone_number() {
        return mobile_hone_number;
    }

    public void setMobile_hone_number(String mobile_hone_number) {
        this.mobile_hone_number = mobile_hone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getRelationship_status() {
        return relationship_status;
    }

    public void setRelationship_status(String relationship_status) {
        this.relationship_status = relationship_status;
    }

    public int getDependants() {
        return dependants;
    }

    public void setDependants(int dependants) {
        this.dependants = dependants;
    }

    public String[] getDob_of_dependants() {
        return dob_of_dependants;
    }

    public void setDob_of_dependants(String[] dob_of_dependants) {
        this.dob_of_dependants = dob_of_dependants;
    }

    public String getHighest_education_attained() {
        return highest_education_attained;
    }

    public void setHighest_education_attained(String highest_education_attained) {
        this.highest_education_attained = highest_education_attained;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public boolean isKyc_status() {
        return kyc_status;
    }

    public void setKyc_status(boolean kyc_status) {
        this.kyc_status = kyc_status;
    }

    public String getLast_ok_date() {
        return last_ok_date;
    }

    public void setLast_ok_date(String last_ok_date) {
        this.last_ok_date = last_ok_date;
    }

    public Image getFace_image() {
        return face_image;
    }

    public void setFace_image(Image face_image) {
        this.face_image = face_image;
    }
}
