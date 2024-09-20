package com.example.dinnerreserver.model;

public class Booking {
    private int bookingId;
    private int userId;
    private int restaurantId;
    private int numberOfPeople;
    private float bookingTime;

    // Constructor, getters, and setters
    public Booking(int userId, int restaurantId, int numberOfPeople, float bookingTime) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.numberOfPeople = numberOfPeople;
        this.bookingTime = bookingTime;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public float getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(float bookingTime) {
        this.bookingTime = bookingTime;
    }
}
