package com.example.pulkit.boozingo.model;

public class detailsClub {

    String club_name,club_address,club_details,club_cost,club_geolocation,club_time,club_not_working_day,
            club_contact,club_booze_served,club_food,club_sitting_facility,club_music,club_payment,club_ac,club_images;

    public detailsClub() {
    }

    public detailsClub(String club_address, String club_booze_served, String club_contact, String club_cost, String club_details, String club_food, String club_geolocation, String club_images, String club_music, String club_name, String club_not_working_day, String club_payment, String club_sitting_facility, String club_time,String club_ac) {
        this.club_address = club_address;
        this.club_booze_served = club_booze_served;
        this.club_contact = club_contact;
        this.club_cost = club_cost;
        this.club_details = club_details;
        this.club_food = club_food;
        this.club_geolocation = club_geolocation;
        this.club_images = club_images;
        this.club_music = club_music;
        this.club_name = club_name;
        this.club_not_working_day = club_not_working_day;
        this.club_payment = club_payment;
        this.club_sitting_facility = club_sitting_facility;
        this.club_time = club_time;
        this.club_ac = club_ac;
    }

    public String getClub_address() {
        return club_address;
    }

    public void setClub_address(String club_address) {
        this.club_address = club_address;
    }

    public String getClub_booze_served() {
        return club_booze_served;
    }

    public void setClub_booze_served(String club_booze_served) {
        this.club_booze_served = club_booze_served;
    }

    public String getClub_contact() {
        return club_contact;
    }

    public void setClub_contact(String club_contact) {
        this.club_contact = club_contact;
    }

    public String getClub_cost() {
        return club_cost;
    }

    public void setClub_cost(String club_cost) {
        this.club_cost = club_cost;
    }

    public String getClub_details() {
        return club_details;
    }

    public void setClub_details(String club_details) {
        this.club_details = club_details;
    }

    public String getClub_food() {
        return club_food;
    }

    public void setClub_food(String club_food) {
        this.club_food = club_food;
    }

    public String getClub_geolocation() {
        return club_geolocation;
    }

    public void setClub_geolocation(String club_geolocation) {
        this.club_geolocation = club_geolocation;
    }

    public String getClub_images() {
        return club_images;
    }

    public void setClub_images(String club_images) {
        this.club_images = club_images;
    }

    public String getClub_music() {
        return club_music;
    }

    public void setClub_music(String club_music) {
        this.club_music = club_music;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_not_working_day() {
        return club_not_working_day;
    }

    public void setClub_not_working_day(String club_not_working_day) {
        this.club_not_working_day = club_not_working_day;
    }

    public String getClub_payment() {
        return club_payment;
    }

    public void setClub_payment(String club_payment) {
        this.club_payment = club_payment;
    }

    public String getClub_sitting_facility() {
        return club_sitting_facility;
    }

    public void setClub_sitting_facility(String club_sitting_facility) {
        this.club_sitting_facility = club_sitting_facility;
    }

    public String getClub_time() {
        return club_time;
    }

    public void setClub_time(String club_time) {
        this.club_time = club_time;
    }

    public String getClub_ac() {
        return club_ac;
    }

    public void setClub_ac(String club_ac) {
        this.club_ac = club_ac;
    }
}
