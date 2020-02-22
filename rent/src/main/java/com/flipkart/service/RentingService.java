package com.flipkart.service;

public interface RentingService {
    public void addBranch(String name);
    public void set_vehicle(String name, String type, int count, int price);
    public void rent_vehicle(String type, String time, int duration);
    public void system_view_for_time_slot(String time1, String time2);
}
