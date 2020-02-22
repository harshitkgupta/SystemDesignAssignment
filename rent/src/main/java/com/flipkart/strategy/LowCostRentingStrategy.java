package com.flipkart.strategy;

import com.flipkart.model.BookingItem;
import com.flipkart.model.Location;
import com.flipkart.model.RentBooking;
import com.flipkart.model.Vehicle;

import java.awt.print.Book;
import java.util.*;

public class LowCostRentingStrategy implements RentingStrategy {


    Map<String, ArrayList<BookingItem>> items;
    Comparator<BookingItem> comparator = new Comparator<BookingItem>() {
        @Override
        public int compare(BookingItem o1, BookingItem o2) {
            if(o1.getPrice() != o2.getPrice())
                return o2.getPrice() - o1.getPrice();
            else
                return o2.getLocation().getId().compareTo(o1.getLocation().getId());
        }
    };

    public LowCostRentingStrategy() {
        this.items = new HashMap<>();
    }


    @Override
    public boolean addItem(String type, Location location, int price, int count) {
        if(!items.containsKey(type))
            items.put(type,new ArrayList<>());

        List<BookingItem> list = items.get(type);
        boolean found = false;
        for(BookingItem l:list){
            if(l.getLocation().getName().equalsIgnoreCase(location.getName())) {
                l.setPrice(price);
                l.setCount(count);
                found = true;
                break;
            }
        }
        if(!found) {
            BookingItem item = new BookingItem(location, price,count, type);
            list.add(item);
        }
        return true;
    }

    @Override
    public RentBooking getBooking(String type, String time, int duration) {
        ArrayList<BookingItem>  item = items.get(type);
        if(!items.keySet().contains(type)) {
            System.out.println("type not registered");
            return null;
        }
        BookingItem picked = null;
        RentBooking booking = null;

        if(item.size()>0) {
            Collections.sort(item, comparator);

            picked = item.get(item.size()-1);

            picked.decrease();
            if(picked.getCount()==0)
                item.remove(item.size() - 1);
//        if(picked == null);
//            throw new RuntimeException("No Booking");
            booking = new RentBooking(picked, time, duration);
        }
         return booking;
    }

    @Override
    public void closeBooking(RentBooking booking) {
        ArrayList<BookingItem>  list = items.get(booking.getItem().getType());
        boolean found = false;
        for(BookingItem i : list){
            if(booking.getItem().getLocation().getName().equals(i.getLocation().getName())){
                    i.increase();
                    found = true;
                    break;
            }
        }
        if(!found) {
            booking.getItem().setCount(1);
            list.add(booking.getItem());
        }


    }

    @Override
    public void system_view_for_time_slot(Set<String> keySet) {
        Map<String,List<BookingItem>> map = new HashMap();
        for(String location: keySet) {
            map.put(location, new ArrayList<>());
        }
            for (List<BookingItem> it : items.values()) {

                for (BookingItem b : it) {
                        map.get(b.getLocation().getName()).add(b);
                }
            }

        for(String location: keySet) {
            System.out.println(location);
            for(BookingItem b :map.get(location)){
                System.out.println("Count "+b.getCount()+" Type "+b.getType()+" cost: Rs "+b.getPrice());
            }
        }

    }

}
