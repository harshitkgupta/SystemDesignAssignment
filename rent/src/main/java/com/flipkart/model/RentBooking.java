package com.flipkart.model;

public class RentBooking {
    private  BookingItem item;
    private  String time;
    private  int duration;

    public RentBooking(BookingItem item, String time, int duration) {
        this.item = item;
        this.time = time;
        this.duration = duration;
    }

    public BookingItem getItem() {
        return item;
    }

    public void setItem(BookingItem item) {
        this.item = item;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }
}
