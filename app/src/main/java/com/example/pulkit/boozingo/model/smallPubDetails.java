package com.example.pulkit.boozingo.model;

public class smallPubDetails {

    String id="", pub_name="", pub_address="", pub_cost="", pub_time="", pub_not_working_day="", pub_contact,pub_pic="";

    smallPubDetails() {

    }

    public smallPubDetails(String pub_address, String pub_contact, String pub_cost, String pub_name, String pub_not_working_day, String pub_time, String id, String pic) {
        this.pub_address = pub_address;
        this.pub_contact = pub_contact;
        this.pub_cost = pub_cost;
        this.pub_name = pub_name;
        this.pub_not_working_day = pub_not_working_day;
        this.pub_time = pub_time;
        this.id = id;
        this.pub_pic = pic;
    }

    public String getId() {
        return id;
    }

    public String getPub_address() {
        return pub_address.trim();
    }

    public String getPub_contact() {
        return pub_contact.trim();
    }

    public String getPub_cost() {
        return pub_cost.trim();
    }

    public String getPub_name() {
        return pub_name.trim();
    }

    public String getPub_not_working_day() {
        return pub_not_working_day.trim();
    }

    public String getPub_pic() {
        return pub_pic.trim();
    }

    public String getPub_time() {
        return pub_time.trim();
    }

    public void setPub_pic(String pub_pic) {
        this.pub_pic = pub_pic.trim();
    }
}
