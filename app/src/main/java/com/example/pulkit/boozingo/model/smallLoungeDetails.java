package com.example.pulkit.boozingo.model;

public class smallLoungeDetails {

    String id, lounge_name, lounge_address, lounge_cost, lounge_time, lounge_not_working_day, lounge_contact,lounge_pic;

    smallLoungeDetails() {

    }

    public smallLoungeDetails(String lounge_address, String lounge_contact, String lounge_cost, String lounge_name, String lounge_not_working_day, String lounge_time, String id, String pic) {
        this.lounge_address = lounge_address;
        this.lounge_contact = lounge_contact;
        this.lounge_cost = lounge_cost;
        this.lounge_name = lounge_name;
        this.lounge_not_working_day = lounge_not_working_day;
        this.lounge_time = lounge_time;
        this.id = id;
        this.lounge_pic = pic;
    }

    public String getLounge_address() {
        return lounge_address;
    }

    public void setLounge_address(String lounge_address) {
        this.lounge_address = lounge_address;
    }

    public String getLounge_contact() {
        return lounge_contact;
    }

    public void setLounge_contact(String lounge_contact) {
        this.lounge_contact = lounge_contact;
    }

    public String getLounge_cost() {
        return lounge_cost;
    }

    public void setLounge_cost(String lounge_cost) {
        this.lounge_cost = lounge_cost;
    }

    public String getLounge_name() {
        return lounge_name;
    }

    public void setLounge_name(String lounge_name) {
        this.lounge_name = lounge_name;
    }

    public String getLounge_not_working_day() {
        return lounge_not_working_day;
    }

    public void setLounge_not_working_day(String lounge_not_working_day) {
        this.lounge_not_working_day = lounge_not_working_day;
    }

    public String getLounge_time() {
        return lounge_time;
    }

    public void setLounge_time(String lounge_time) {
        this.lounge_time = lounge_time;
    }

    public String getId() {
        return id;
    }

    public String getLounge_pic() {
        return lounge_pic;
    }

    public void setLounge_pic(String lounge_pic) {
        this.lounge_pic = lounge_pic;
    }


}
