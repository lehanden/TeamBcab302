package com.example.dinnerreserver.model;

import java.util.List;

/**
 * Interface for the Bookings Data Access Object that handles
 * the CRUD operations for the User class with the database
 */
public interface IBookingDAO {

    /**
     * Adds a new booking to the database
     * @param booking The booking to add
     */
    public void addBooking(Booking booking);

    /**
     * Counts the number of bookings in a timeslot for a restaurant
     * @param restaurantId The restaurant to check
     * @param timeSlot The timeslot to check
     * @return
     */
    public int countBookingsForTimeSlot(int restaurantId, String timeSlot);

    /**
     * Deletes a booking from the database
     * @param booking The booking to delete
     */
    public void deleteBooking(Booking booking);

    /**
     * Updates an existing booking in the database
     * @param booking The booking to update
     */
    public void updateBooking(Booking booking);

    /**
     * Retrieves a booking from the database
     * @param id The id of the booking to retrieve
     * @return The booking with the given id, or null if not found
     */
    public Booking getBooking(int id);

    /**
     * Retrieves all bookings from the database
     * @return A list of all bookings in the database
     */
    public List<Booking> getAllBookings();

}


