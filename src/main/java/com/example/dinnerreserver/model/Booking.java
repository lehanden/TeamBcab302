package com.example.dinnerreserver.model;

/**
 * A simple class for the bookings containing the same parameters as the bookings database
 */
public class Booking {
    private int bookingId;
    private int userId;
    private int restaurantId;
    private int numberOfPeople;
    private String bookingTime;

    /**
     * Constructor methods for each of the booking parameters
     * @param userId The user id that created the booking
     * @param restaurantId The id of the restaurant that has been booked
     * @param numberOfPeople The number of people at a booking
     * @param bookingTime The time the booking was made for
     */
    public Booking(int userId, int restaurantId, int numberOfPeople, String bookingTime) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.numberOfPeople = numberOfPeople;
        this.bookingTime = bookingTime;
    }

    /**
     * Gets the current booking id
     * @return The id of the current booking
     */
    public int getBookingId() {
        return bookingId;
    }

    /**
     * Updates the current booking to use the given booking id
     * @param bookingId The new booking id
     */
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Gets the current user id
     * @return The id of the current user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Updates the current user id to use the given user id
     * @param userId The new user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the current restaurant id
     * @return The id of the currently selected restaurant
     */
    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Updates the current restaurant id to use the given restaurant id
     * @param restaurantId The new user id
     */
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Gets the number of people for the current booking
     * @return Count of people in the current booking
     */
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Sets the number of people for the current booking
     * @param numberOfPeople The new number of people
     */
    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * Gets the booking time for the current booking
     * @return Time of the current booking
     */
    public String getBookingTime() {
        return bookingTime;
    }

    /**
     * Updates the booking time of the current booking
     * @param bookingTime The new booking time
     */
    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }
}
