package com.example.pulkit.boozingo.model;

public class smallClubDetails {

    String id, night_club_name, night_club_address, night_club_cost, night_club_time, night_club_not_working_day, night_club_contact,night_club_pic;

    smallClubDetails() {

    }

    public smallClubDetails(String night_club_address, String night_club_contact, String night_club_cost, String night_club_name, String night_club_not_working_day, String night_club_time, String id, String pic) {
        this.night_club_address = night_club_address;
        this.night_club_contact = night_club_contact;
        this.night_club_cost = night_club_cost;
        this.night_club_name = night_club_name;
        this.night_club_not_working_day = night_club_not_working_day;
        this.night_club_time = night_club_time;
        this.id = id;
        this.night_club_pic = pic;
    }

    public String getId() {
        return id;
    }

    public String getNight_club_address() {
        return night_club_address;
    }

    public String getNight_club_contact() {
        return night_club_contact;
    }

    public String getNight_club_cost() {
        return night_club_cost;
    }

    public String getNight_club_name() {
        return night_club_name;
    }

    public String getNight_club_not_working_day() {
        return night_club_not_working_day;
    }

    public String getNight_club_pic() {
        return night_club_pic;
    }

    public String getNight_club_time() {
        return night_club_time;
    }

    public void setNight_club_pic(String night_club_pic) {
        this.night_club_pic = night_club_pic;
    }
}
