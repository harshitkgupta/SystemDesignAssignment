package com.flipkart.strategy;

import com.flipkart.model.Location;
import com.flipkart.model.RentBooking;

import java.util.Set;

public interface RentingStrategy {
    public boolean addItem(String type, Location location, int price, int count);
    public RentBooking getBooking(String type, String time, int duration);
    public void closeBooking(RentBooking booking);

    void system_view_for_time_slot(Set<String> keySet);
}
