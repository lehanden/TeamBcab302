package com.example.dinnerreserver.model;

import java.sql.*;
import java.util.List;

public class SqliteBookingDAO implements IBookingDAO {

    private Connection connection;

    public SqliteBookingDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS bookings ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "user_id INTEGER NOT NULL,"
                    + "restaurant_id INTEGER NOT NULL,"
                    + "number_of_people INTEGER NOT NULL,"
                    + "booking_time FLOAT NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // todo
    @Override
    public void addBooking(Booking booking) {
        String query = "INSERT INTO bookings (user_id, restaurant_id, number_of_people, booking_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRestaurantId());
            pstmt.setInt(3, booking.getNumberOfPeople());

            pstmt.setString(4, booking.getBookingTime());
            pstmt.executeUpdate();

            // Get the generated booking ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int countBookingsForTimeSlot(int restaurantId, String timeSlot) {
        String query = "SELECT SUM(number_of_people) AS total_people FROM bookings WHERE restaurant_id = ? AND booking_time = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, restaurantId);
            pstmt.setString(2, timeSlot);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total_people"); // Get the total number of people
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }



    @Override
    public void deleteBooking(Booking booking) {

    }

    @Override
    public void updateBooking(Booking booking) {

    }

    @Override
    public Booking getBooking(int id) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }
}