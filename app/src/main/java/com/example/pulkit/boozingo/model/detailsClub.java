package com.example.pulkit.boozingo.model;

public class detailsClub {

    String night_club_name,night_club_address,night_club_details,night_club_cost,night_club_geolocation,night_club_time,night_club_not_working_day,
            night_club_contact,night_club_booze_served,night_club_food,night_club_sitting_facility,night_club_music,night_club_payment,night_club_ac,night_club_images;

    public detailsClub() {
    }

    public detailsClub(String night_club_address, String night_club_booze_served, String night_club_contact, String night_club_cost, String night_club_details, String night_club_food, String night_club_geolocation, String night_club_images, String night_club_music, String night_club_name, String night_club_not_working_day, String night_club_payment, String night_club_sitting_facility, String night_club_time,String night_club_ac) {
        this.night_club_address = night_club_address;
        this.night_club_booze_served = night_club_booze_served;
        this.night_club_contact = night_club_contact;
        this.night_club_cost = night_club_cost;
        this.night_club_details = night_club_details;
        this.night_club_food = night_club_food;
        this.night_club_geolocation = night_club_geolocation;
        this.night_club_images = night_club_images;
        this.night_club_music = night_club_music;
        this.night_club_name = night_club_name;
        this.night_club_not_working_day = night_club_not_working_day;
        this.night_club_payment = night_club_payment;
        this.night_club_sitting_facility = night_club_sitting_facility;
        this.night_club_time = night_club_time;
        this.night_club_ac = night_club_ac;
    }

    public String getNight_club_ac() {
        return night_club_ac;
    }

    public String getNight_club_address() {
        return night_club_address;
    }

    public String getNight_club_booze_served() {
        return night_club_booze_served;
    }

    public String getNight_club_contact() {
        return night_club_contact;
    }

    public String getNight_club_cost() {
        return night_club_cost;
    }

    public String getNight_club_details() {
        return night_club_details;
    }

    public String getNight_club_food() {
        return night_club_food;
    }

    public String getNight_club_geolocation() {
        return night_club_geolocation;
    }

    public String getNight_club_images() {
        return night_club_images;
    }

    public String getNight_club_music() {
        return night_club_music;
    }

    public String getNight_club_name() {
        return night_club_name;
    }

    public String getNight_club_not_working_day() {
        return night_club_not_working_day;
    }

    public String getNight_club_payment() {
        return night_club_payment;
    }

    public String getNight_club_sitting_facility() {
        return night_club_sitting_facility;
    }

    public String getNight_club_time() {
        return night_club_time;
    }
}
