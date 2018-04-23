package com.boozingo.model;

public class detailsPub {

    String pub_name, pub_address, pub_details, pub_cost, pub_geolocation, pub_time, pub_not_working_day,
            pub_contact, pub_booze_served, pub_food, pub_sitting_facility, pub_music, pub_payment, pub_ac, pub_images;

    public detailsPub() {
    }

    public detailsPub(String pub_address, String pub_booze_served, String pub_contact, String pub_cost, String pub_details, String pub_food, String pub_geolocation, String pub_images, String pub_music, String pub_name, String pub_not_working_day, String pub_payment, String pub_sitting_facility, String pub_time, String pub_ac) {
        this.pub_address = pub_address;
        this.pub_booze_served = pub_booze_served;
        this.pub_contact = pub_contact;
        this.pub_cost = pub_cost;
        this.pub_details = pub_details;
        this.pub_food = pub_food;
        this.pub_geolocation = pub_geolocation;
        this.pub_images = pub_images;
        this.pub_music = pub_music;
        this.pub_name = pub_name;
        this.pub_not_working_day = pub_not_working_day;
        this.pub_payment = pub_payment;
        this.pub_sitting_facility = pub_sitting_facility;
        this.pub_time = pub_time;
        this.pub_ac = pub_ac;
    }

    public String getPub_ac() {
        return pub_ac;
    }

    public String getPub_address() {
        return pub_address;
    }

    public String getPub_booze_served() {
        return pub_booze_served;
    }

    public String getPub_contact() {
        return pub_contact;
    }

    public String getPub_cost() {
        return pub_cost;
    }

    public String getPub_details() {
        return pub_details;
    }

    public String getPub_food() {
        return pub_food;
    }

    public String getPub_geolocation() {
        return pub_geolocation;
    }

    public String getPub_images() {
        return pub_images;
    }

    public String getPub_music() {
        return pub_music;
    }

    public String getPub_name() {
        return pub_name;
    }

    public String getPub_not_working_day() {
        return pub_not_working_day;
    }

    public String getPub_payment() {
        return pub_payment;
    }

    public String getPub_sitting_facility() {
        return pub_sitting_facility;
    }

    public String getPub_time() {
        return pub_time;
    }
}