package com.boozingo.model;

public class smallBarDetails {

    String id="", bar_name="", bar_address="", bar_cost="", bar_time="", bar_not_working_day="", bar_contact="",bar_icon="";

    smallBarDetails() {

    }

    public smallBarDetails(String bar_address, String bar_contact, String bar_cost, String bar_name, String bar_not_working_day, String bar_time, String id, String icon) {
        this.bar_address = bar_address;
        this.bar_contact = bar_contact;
        this.bar_cost = bar_cost;
        this.bar_name = bar_name;
        this.bar_not_working_day = bar_not_working_day;
        this.bar_time = bar_time;
        this.id = id;
        this.bar_icon = icon;
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

    public String getBar_icon() {
        return bar_icon.trim();
    }

    public String getBar_time() {
        return bar_time.trim();
    }

    public String getId() {
        return id.trim();
    }

    public void setBar_icon(String bar_icon) {
        this.bar_icon = bar_icon.trim();
    }
}
