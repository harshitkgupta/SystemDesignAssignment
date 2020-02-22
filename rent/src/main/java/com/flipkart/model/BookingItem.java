package com.flipkart.model;


public class BookingItem {
    private String type;
    private int count;
    private Location location;
    private int price;

    public BookingItem(Location location, int price, int count, String type) {
        this.type = type;
        this.location = location;
        this.price = price;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increase() {
        this.count++;
    }
    public void decrease() {
        this.count--;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
