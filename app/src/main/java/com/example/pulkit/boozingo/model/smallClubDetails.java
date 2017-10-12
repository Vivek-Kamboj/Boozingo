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

    public String getClub_address() {
        return night_club_address;
    }

    public void setClub_address(String night_club_address) {
        this.night_club_address = night_club_address;
    }

    public String getClub_contact() {
        return night_club_contact;
    }

    public void setClub_contact(String night_club_contact) {
        this.night_club_contact = night_club_contact;
    }

    public String getClub_cost() {
        return night_club_cost;
    }

    public void setClub_cost(String night_club_cost) {
        this.night_club_cost = night_club_cost;
    }

    public String getClub_name() {
        return night_club_name;
    }

    public void setClub_name(String night_club_name) {
        this.night_club_name = night_club_name;
    }

    public String getClub_not_working_day() {
        return night_club_not_working_day;
    }

    public void setClub_not_working_day(String night_club_not_working_day) {
        this.night_club_not_working_day = night_club_not_working_day;
    }

    public String getClub_time() {
        return night_club_time;
    }

    public void setClub_time(String night_club_time) {
        this.night_club_time = night_club_time;
    }

    public String getId() {
        return id;
    }

    public String getClub_pic() {
        return night_club_pic;
    }

    public void setClub_pic(String night_club_pic) {
        this.night_club_pic = night_club_pic;
    }

/*
    public void setId(String id) {
        this.id = id;
    }*/

}
