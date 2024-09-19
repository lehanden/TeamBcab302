package com.example.dinnerreserver.model;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "INSERT INTO bookings(user_id, restaurant_id, number_of_people, booking_time) VALUES(?, ?, ?, ?)";

        try (Connection conn = SqliteConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getRestaurantId());
            pstmt.setInt(3, booking.getNumberOfPeople());
            //pstmt.setfloat(4, booking.getBookingTime());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
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