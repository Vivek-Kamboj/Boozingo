package com.example.pulkit.boozingo.model;

public class smallBarDetails {

    String id="", bar_name="", bar_address="", bar_cost="", bar_time="", bar_not_working_day="", bar_contact="",bar_pic="";

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
        return bar_address.trim();
    }

    public String getBar_contact() {
        return bar_contact.trim();
    }

    public String getBar_cost() {
        return bar_cost.trim();
    }

    public String getBar_name() {
        return bar_name.trim();
    }

    public String getBar_not_working_day() {
        return bar_not_working_day.trim();
    }

    public String getBar_pic() {
        return bar_pic.trim();
    }

    public String getBar_time() {
        return bar_time.trim();
    }

    public String getId() {
        return id.trim();
    }

    public void setBar_pic(String bar_pic) {
        this.bar_pic = bar_pic.trim();
    }
}
