package com.flipkart.dao;

import com.flipkart.model.RentBooking;
import com.flipkart.model.Vehicle;
import com.flipkart.strategy.LowCostRentingStrategy;
import com.flipkart.strategy.RentingStrategy;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public interface RentingDAO {

    void set_vehicle(String name, String type, int count, int price);

    void addBranch(String name);

    RentBooking rent_vehicle(String type, String time, int duration);

    void system_view_for_time_slot(String time1, String time2);
}
