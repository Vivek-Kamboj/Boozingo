package com.boozingo.model;

public class smallLoungeDetails {

    String id="", lounge_name="", lounge_address="", lounge_cost="", lounge_time="", lounge_not_working_day="",
            lounge_contact="", lounge_icon="";

    smallLoungeDetails() {

    }

    public smallLoungeDetails(String lounge_address, String lounge_contact, String lounge_cost, String lounge_name, String lounge_not_working_day, String lounge_time, String id, String icon) {
        this.lounge_address = lounge_address;
        this.lounge_contact = lounge_contact;
        this.lounge_cost = lounge_cost;
        this.lounge_name = lounge_name;
        this.lounge_not_working_day = lounge_not_working_day;
        this.lounge_time = lounge_time;
        this.id = id;
        this.lounge_icon = icon;
    }

    public String getId() {
        return id.trim();
    }

    public String getLounge_address() {
        return lounge_address.trim();
    }

    public String getLounge_contact() {
        return lounge_contact.trim();
    }

    public String getLounge_cost() {
        return lounge_cost.trim();
    }

    public String getLounge_name() {
        return lounge_name.trim();
    }

    public String getLounge_not_working_day() {
        return lounge_not_working_day.trim();
    }

    public String getLounge_icon() {
        return lounge_icon.trim();
    }

    public String getLounge_time() {
        return lounge_time.trim();
    }

    public void setLounge_icon(String lounge_icon) {
        this.lounge_icon = lounge_icon;
    }
}