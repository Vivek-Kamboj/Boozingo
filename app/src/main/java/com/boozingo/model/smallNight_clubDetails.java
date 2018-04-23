package com.boozingo.model;

public class smallNight_clubDetails {

    String id="", night_club_name="", night_club_address="", night_club_cost="", night_club_time="",
            night_club_not_working_day="", night_club_contact="",night_club_icon="";

    smallNight_clubDetails() {

    }

    public smallNight_clubDetails(String night_club_address, String night_club_contact, String night_club_cost, String night_club_name, String night_club_not_working_day, String night_club_time, String id, String icon) {
        this.night_club_address = night_club_address;
        this.night_club_contact = night_club_contact;
        this.night_club_cost = night_club_cost;
        this.night_club_name = night_club_name;
        this.night_club_not_working_day = night_club_not_working_day;
        this.night_club_time = night_club_time;
        this.id = id;
        this.night_club_icon = icon;
    }

    public String getId() {
        return id.trim();
    }

    public String getNight_club_address() {
        return night_club_address.trim();
    }

    public String getNight_club_contact() {
        return night_club_contact.trim();
    }

    public String getNight_club_cost() {
        return night_club_cost.trim();
    }

    public String getNight_club_name() {
        return night_club_name.trim();
    }

    public String getNight_club_not_working_day() {
        return night_club_not_working_day.trim();
    }

    public String getNight_club_icon() {
        return night_club_icon.trim();
    }

    public String getNight_club_time() {
        return night_club_time.trim();
    }

    public void setNight_club_icon(String night_club_icon) {
        this.night_club_icon = night_club_icon.trim();
    }
}
