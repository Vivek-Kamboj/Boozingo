package com.example.pulkit.boozingo.model;

public class BarDetails {

    String name,type,address,timing,open,speciality,coordinates,website,facebook,contact;

    BarDetails(){

    }

    public BarDetails(String address, String contact, String coordinates, String facebook, String name, String open, String speciality, String timing, String type, String website) {

        this.address = address;
        this.contact = contact;
        this.coordinates = coordinates;
        this.facebook = facebook;
        this.name = name;
        this.open = open;
        this.speciality = speciality;
        this.timing = timing;
        this.type = type;
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
