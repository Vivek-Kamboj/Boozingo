package com.boozingo.model;

public class smallBeer_shopDetails {

    String id="", beer_shop_name="", beer_shop_address="", beer_shop_cost="",
            beer_shop_time="", beer_shop_not_working_day="", beer_shop_contact="",beer_shop_icon="";

    smallBeer_shopDetails() {

    }

    public smallBeer_shopDetails(String beer_shop_address, String beer_shop_contact, String beer_shop_cost, String beer_shop_name, String beer_shop_not_working_day, String beer_shop_time, String id, String icon) {
        this.beer_shop_address = beer_shop_address;
        this.beer_shop_contact = beer_shop_contact;
        this.beer_shop_cost = beer_shop_cost;
        this.beer_shop_name = beer_shop_name;
        this.beer_shop_not_working_day = beer_shop_not_working_day;
        this.beer_shop_time = beer_shop_time;
        this.id = id;
        this.beer_shop_icon = icon;
    }

    public String getBeer_shop_address() {
        return beer_shop_address.trim();
    }

    public String getBeer_shop_contact() {
        return beer_shop_contact.trim();
    }

    public String getBeer_shop_cost() {
        return beer_shop_cost.trim();
    }

    public String getBeer_shop_name() {
        return beer_shop_name.trim();
    }

    public String getBeer_shop_not_working_day() {
        return beer_shop_not_working_day.trim();
    }

    public String getBeer_shop_icon() {
        return beer_shop_icon.trim();
    }

    public String getBeer_shop_time() {
        return beer_shop_time.trim();
    }

    public String getId() {
        return id.trim();
    }

    public void setBeer_shop_icon(String beer_shop_icon) {
        this.beer_shop_icon = beer_shop_icon;
    }
}
