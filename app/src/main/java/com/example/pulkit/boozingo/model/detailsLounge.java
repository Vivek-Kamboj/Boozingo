package com.example.pulkit.boozingo.model;

public class detailsLounge {

    String lounge_name,lounge_address,lounge_details,lounge_cost,lounge_geolocation,lounge_time,lounge_not_working_day,
            lounge_contact,lounge_booze_served,lounge_food,lounge_sitting_facility,lounge_music,lounge_payment,lounge_ac,lounge_images;

    public detailsLounge() {
    }

    public detailsLounge(String lounge_address, String lounge_booze_served, String lounge_contact, String lounge_cost, String lounge_details, String lounge_food, String lounge_geolocation, String lounge_images, String lounge_music, String lounge_name, String lounge_not_working_day, String lounge_payment, String lounge_sitting_facility, String lounge_time,String lounge_ac) {
        this.lounge_address = lounge_address;
        this.lounge_booze_served = lounge_booze_served;
        this.lounge_contact = lounge_contact;
        this.lounge_cost = lounge_cost;
        this.lounge_details = lounge_details;
        this.lounge_food = lounge_food;
        this.lounge_geolocation = lounge_geolocation;
        this.lounge_images = lounge_images;
        this.lounge_music = lounge_music;
        this.lounge_name = lounge_name;
        this.lounge_not_working_day = lounge_not_working_day;
        this.lounge_payment = lounge_payment;
        this.lounge_sitting_facility = lounge_sitting_facility;
        this.lounge_time = lounge_time;
        this.lounge_ac = lounge_ac;
    }

    public String getLounge_ac() {
        return lounge_ac;
    }

    public String getLounge_address() {
        return lounge_address;
    }

    public String getLounge_booze_served() {
        return lounge_booze_served;
    }

    public String getLounge_contact() {
        return lounge_contact;
    }

    public String getLounge_cost() {
        return lounge_cost;
    }

    public String getLounge_details() {
        return lounge_details;
    }

    public String getLounge_food() {
        return lounge_food;
    }

    public String getLounge_geolocation() {
        return lounge_geolocation;
    }

    public String getLounge_images() {
        return lounge_images;
    }

    public String getLounge_music() {
        return lounge_music;
    }

    public String getLounge_name() {
        return lounge_name;
    }

    public String getLounge_not_working_day() {
        return lounge_not_working_day;
    }

    public String getLounge_payment() {
        return lounge_payment;
    }

    public String getLounge_sitting_facility() {
        return lounge_sitting_facility;
    }

    public String getLounge_time() {
        return lounge_time;
    }
}
