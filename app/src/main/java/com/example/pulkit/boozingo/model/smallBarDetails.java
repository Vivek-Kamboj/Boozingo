package com.example.pulkit.boozingo.model;

public class smallBarDetails {

    String id, bar_name, bar_address, bar_cost, bar_time, bar_not_working_day, bar_contact,bar_pic;

    smallBarDetails() {

    }

    public smallBarDetails(String bar_address, String bar_contact, String bar_cost, String bar_name, String bar_not_working_day, String bar_time, String id, String pic) {
        this.bar_address = bar_address;
        this.bar_contact = bar_contact;
        this.bar_cost = bar_cost;
        this.bar_name = bar_name;
        this.bar_not_working_day = bar_not_working_day;
        this.bar_time = bar_time;
        this.id = id;
        this.bar_pic = pic;
    }

    public String getBar_address() {
        return bar_address;
    }

    public void setBar_address(String bar_address) {
        this.bar_address = bar_address;
    }

    public String getBar_contact() {
        return bar_contact;
    }

    public void setBar_contact(String bar_contact) {
        this.bar_contact = bar_contact;
    }

    public String getBar_cost() {
        return bar_cost;
    }

    public void setBar_cost(String bar_cost) {
        this.bar_cost = bar_cost;
    }

    public String getBar_name() {
        return bar_name;
    }

    public void setBar_name(String bar_name) {
        this.bar_name = bar_name;
    }

    public String getBar_not_working_day() {
        return bar_not_working_day;
    }

    public void setBar_not_working_day(String bar_not_working_day) {
        this.bar_not_working_day = bar_not_working_day;
    }

    public String getBar_time() {
        return bar_time;
    }

    public void setBar_time(String bar_time) {
        this.bar_time = bar_time;
    }

    public String getId() {
        return id;
    }

    public String getBar_pic() {
        return bar_pic;
    }

    public void setBar_pic(String bar_pic) {
        this.bar_pic = bar_pic;
    }

/*
    public void setId(String id) {
        this.id = id;
    }*/

}
