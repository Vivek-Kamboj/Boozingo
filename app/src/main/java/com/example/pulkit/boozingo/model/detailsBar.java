package com.example.pulkit.boozingo.model;

public class detailsBar {

    String bar_name,bar_address,bar_details,bar_cost,bar_geolocation,bar_time,bar_not_working_day,
            bar_contact,bar_booze_served,bar_food,bar_sitting_facility,bar_music,bar_payment,bar_ac,bar_images;

    public detailsBar() {
    }

    public detailsBar(String bar_address, String bar_booze_served, String bar_contact, String bar_cost, String bar_details, String bar_food, String bar_geolocation, String bar_images, String bar_music, String bar_name, String bar_not_working_day, String bar_payment, String bar_sitting_facility, String bar_time,String bar_ac) {
        this.bar_address = bar_address;
        this.bar_booze_served = bar_booze_served;
        this.bar_contact = bar_contact;
        this.bar_cost = bar_cost;
        this.bar_details = bar_details;
        this.bar_food = bar_food;
        this.bar_geolocation = bar_geolocation;
        this.bar_images = bar_images;
        this.bar_music = bar_music;
        this.bar_name = bar_name;
        this.bar_not_working_day = bar_not_working_day;
        this.bar_payment = bar_payment;
        this.bar_sitting_facility = bar_sitting_facility;
        this.bar_time = bar_time;
        this.bar_ac = bar_ac;
    }

    public String getBar_ac() {
        return bar_ac;
    }

    public String getBar_address() {
        return bar_address;
    }

    public String getBar_booze_served() {
        return bar_booze_served;
    }

    public String getBar_contact() {
        return bar_contact;
    }

    public String getBar_cost() {
        return bar_cost;
    }

    public String getBar_details() {
        return bar_details;
    }

    public String getBar_food() {
        return bar_food;
    }

    public String getBar_geolocation() {
        return bar_geolocation;
    }

    public String getBar_images() {
        return bar_images;
    }

    public String getBar_music() {
        return bar_music;
    }

    public String getBar_name() {
        return bar_name;
    }

    public String getBar_not_working_day() {
        return bar_not_working_day;
    }

    public String getBar_payment() {
        return bar_payment;
    }

    public String getBar_sitting_facility() {
        return bar_sitting_facility;
    }

    public String getBar_time() {
        return bar_time;
    }
}
