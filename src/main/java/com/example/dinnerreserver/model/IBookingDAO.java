package com.example.dinnerreserver.model;

import java.util.List;

public interface IBookingDAO {
    public void addBooking(Booking booking);


    public void deleteBooking(Booking booking);

    public void updateBooking(Booking booking);

    public Booking getBooking(int id);

    public List<Booking> getAllBookings();

}


