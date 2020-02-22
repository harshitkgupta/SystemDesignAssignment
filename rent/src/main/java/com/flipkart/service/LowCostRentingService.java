package com.flipkart.service;

import com.flipkart.dao.MemoryRentingDAOImpl;
import com.flipkart.dao.RentingDAO;
import com.flipkart.exception.RentingException;
import com.flipkart.model.BookingItem;
import com.flipkart.model.RentBooking;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LowCostRentingService implements RentingService {
    private static RentingDAO dao = MemoryRentingDAOImpl.getInstance();

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public void addBranch(String name) {
        lock.writeLock().lock();
        try
        {
            dao.addBranch(name);

        }
        catch (Exception e)
        {
            throw new RentingException(e);
        }
        finally
        {
            lock.writeLock().unlock();
        }

    }

    @Override
    public void set_vehicle(String name, String type, int count, int price) {
        lock.writeLock().lock();
        try
        {
            dao.set_vehicle(name,type, count,price);
        }
        catch (Exception e)
        {
            throw new RentingException(e);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void rent_vehicle(String type, String time, int duration) {
        lock.writeLock().lock();
        try
        {
            RentBooking booking = dao.rent_vehicle(type, time, duration);
            if(booking==null){
                System.out.println("No Vehicle");
            }

            else{
            BookingItem item = booking.getItem();
            System.out.println("Booked type: " + item.getType() + " Location: " + item.getLocation().getName() + " Cost: " + item.getPrice());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new RentingException(e);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void system_view_for_time_slot(String time1, String time2) {
        lock.readLock().lock();
        try
        {
            dao.system_view_for_time_slot(time1, time2);
        }
        catch (Exception e)
        {
            throw new RentingException(e);
        }
        finally
        {
            lock.readLock().unlock();
        }
    }
}
