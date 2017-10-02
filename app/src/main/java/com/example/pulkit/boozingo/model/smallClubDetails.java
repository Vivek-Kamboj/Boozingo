package com.example.pulkit.boozingo.model;

public class smallClubDetails {

    String id, club_name, club_address, club_cost, club_time, club_not_working_day, club_contact,club_pic;

    smallClubDetails() {

    }

    public smallClubDetails(String club_address, String club_contact, String club_cost, String club_name, String club_not_working_day, String club_time, String id, String pic) {
        this.club_address = club_address;
        this.club_contact = club_contact;
        this.club_cost = club_cost;
        this.club_name = club_name;
        this.club_not_working_day = club_not_working_day;
        this.club_time = club_time;
        this.id = id;
        this.club_pic = pic;
    }

    public String getClub_address() {
        return club_address;
    }

    public void setClub_address(String club_address) {
        this.club_address = club_address;
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

    public String getClub_time() {
        return club_time;
    }

    public void setClub_time(String club_time) {
        this.club_time = club_time;
    }

    public String getId() {
        return id;
    }

    public String getClub_pic() {
        return club_pic;
    }

    public void setClub_pic(String club_pic) {
        this.club_pic = club_pic;
    }

/*
    public void setId(String id) {
        this.id = id;
    }*/

}
