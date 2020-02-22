package com.flipkart.dao;

import com.flipkart.model.Location;
import com.flipkart.model.RentBooking;
import com.flipkart.model.Vehicle;
import com.flipkart.strategy.LowCostRentingStrategy;
import com.flipkart.strategy.RentingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryRentingDAOImpl implements RentingDAO {

    // Allocation Strategy for parking
    private RentingStrategy strategy;
    // this is per slot - vehicle
    private Map<String, Location> locationMap;
    private List<RentBooking> bookingList;

    @SuppressWarnings("rawtypes")
    private static MemoryRentingDAOImpl instance = null;

    @SuppressWarnings("unchecked")
    public static <T extends Vehicle> MemoryRentingDAOImpl getInstance()
    {
        if (instance == null)
        {
            synchronized (MemoryRentingDAOImpl.class)
            {
                if (instance == null)
                {
                    instance = new MemoryRentingDAOImpl();
                }
            }
        }
        return instance;
    }
    private MemoryRentingDAOImpl()
    {
        this.locationMap = new HashMap<>();
        this.strategy = new LowCostRentingStrategy();
    }

    @Override
    public void set_vehicle(String name, String type, int count, int price) {
        Location location = null;
        if(!locationMap.containsKey(name)) {
            System.out.println("Location not registered "+name);
            return;
        }
        else
            location = locationMap.get(name);

        locationMap.put(name,location);
        strategy.addItem(type, location, price, count);

    }

    @Override
    public void addBranch(String name) {
        Location location = new Location(name, String.valueOf(locationMap.size() + 1));
        locationMap.put(name,location);
    }

    @Override
    public RentBooking rent_vehicle(String type, String time, int duration) {

            return strategy.getBooking(type,time,duration);
    }

    @Override
    public void system_view_for_time_slot(String time1, String time2) {
            strategy.system_view_for_time_slot(locationMap.keySet());
    }

}

