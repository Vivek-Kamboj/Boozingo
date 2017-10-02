package com.example.pulkit.boozingo.model;

public class detailsShop {

    public String beer_shop_name,beer_shop_address,beer_shop_details,beer_shop_cost,beer_shop_geolocation,beer_shop_time,
            beer_shop_not_working_day, beer_shop_contact,beer_shop_booze_served,
            beer_shop_payment,beer_shop_images;

    public detailsShop() {
    }

    public detailsShop(String beer_shop_address, String beer_shop_booze_served, String beer_shop_contact, String beer_shop_cost, String beer_shop_details, String beer_shop_food, String beer_shop_geolocation, String beer_shop_images, String beer_shop_music, String beer_shop_name, String beer_shop_not_working_day, String beer_shop_payment, String beer_shop_sitting_facility, String beer_shop_time,String beer_shop_ac) {
        this.beer_shop_address = beer_shop_address;
        this.beer_shop_booze_served = beer_shop_booze_served;
        this.beer_shop_contact = beer_shop_contact;
        this.beer_shop_cost = beer_shop_cost;
        this.beer_shop_details = beer_shop_details;
        this.beer_shop_geolocation = beer_shop_geolocation;
        this.beer_shop_images = beer_shop_images;
        this.beer_shop_name = beer_shop_name;
        this.beer_shop_not_working_day = beer_shop_not_working_day;
        this.beer_shop_payment = beer_shop_payment;
        this.beer_shop_time = beer_shop_time;
    }

    public String getShop_address() {
        return beer_shop_address;
    }

    public void setShop_address(String beer_shop_address) {
        this.beer_shop_address = beer_shop_address;
    }

    public String getShop_booze_served() {
        return beer_shop_booze_served;
    }

    public void setShop_booze_served(String beer_shop_booze_served) {
        this.beer_shop_booze_served = beer_shop_booze_served;
    }

    public String getShop_contact() {
        return beer_shop_contact;
    }

    public void setShop_contact(String beer_shop_contact) {
        this.beer_shop_contact = beer_shop_contact;
    }

    public String getShop_cost() {
        return beer_shop_cost;
    }

    public void setShop_cost(String beer_shop_cost) {
        this.beer_shop_cost = beer_shop_cost;
    }

    public String getShop_details() {
        return beer_shop_details;
    }

    public void setShop_details(String beer_shop_details) {
        this.beer_shop_details = beer_shop_details;
    }

    public String getShop_geolocation() {
        return beer_shop_geolocation;
    }

    public void setShop_geolocation(String beer_shop_geolocation) {
        this.beer_shop_geolocation = beer_shop_geolocation;
    }

    public String getShop_images() {
        return beer_shop_images;
    }

    public void setShop_images(String beer_shop_images) {
        this.beer_shop_images = beer_shop_images;
    }

    public String getShop_name() {
        return beer_shop_name;
    }

    public void setShop_name(String beer_shop_name) {
        this.beer_shop_name = beer_shop_name;
    }

    public String getShop_not_working_day() {
        return beer_shop_not_working_day;
    }

    public void setShop_not_working_day(String beer_shop_not_working_day) {
        this.beer_shop_not_working_day = beer_shop_not_working_day;
    }

    public String getShop_payment() {
        return beer_shop_payment;
    }

    public void setShop_payment(String beer_shop_payment) {
        this.beer_shop_payment = beer_shop_payment;
    }

    public String getShop_time() {
        return beer_shop_time;
    }

    public void setShop_time(String beer_shop_time) {
        this.beer_shop_time = beer_shop_time;
    }

}
