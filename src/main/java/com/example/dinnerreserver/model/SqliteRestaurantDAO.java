package com.example.dinnerreserver.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteRestaurantDAO implements IRestaurantDAO {
    private Connection connection;

    public SqliteRestaurantDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS restaurants ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "address VARCHAR NOT NULL,"
                    + "description VARCHAR NOT NULL"
                    + "rating FLOAT NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertSampleData() {
        try {
            Statement checkStatement = connection.createStatement();
            ResultSet resultSet = checkStatement.executeQuery("SELECT COUNT(*) FROM restaurants");
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                // Only insert sample data if the table is empty
                Statement insertStatement = connection.createStatement();
                String insertQuery = "INSERT INTO restaurants (name, address, description, rating) VALUES "
                        + "('San Kai Japanese Restaurant', " +
                            "'164 Grey St, South Brisbane', " +
                            "'Japanese classics like sushi, tempura & gyoza served in a chic dining room with sidewalk seating.', " +
                            "'3'),"
                        + "('Olé Restaurant', " +
                            "'Shop/B12 Little Stanley St, South Brisbane', " +
                            "'Vibrant Spanish eatery with tapas, sangria bar and stylish decor, plus an intricate wooden ceiling.', " +
                            "'4.2'),"
                        + "('Geláre South Bank', " +
                            "'3/164 Grey St, South Brisbane QLD 4101', " +
                            "'Ice cream, smoothies and low-fat frozen yoghurt, plus classic breakfasts, in a bright cafe chain.', " +
                            "'3.9')";
                insertStatement.execute(insertQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO restaurant (name, address, description, rating) VALUES (?, ?, ?, ?)");
            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getAddress());
            statement.setString(3, restaurant.getDescription());
            statement.setString(3, restaurant.getRating().toString());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                restaurant.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(Restaurant restaurant) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM restaurant WHERE id = ?");
            statement.setInt(1, restaurant.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name, address, description, rating");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                Float rating = resultSet.getFloat("rating");
                Restaurant restaurant = new Restaurant(name, address, description, rating);
                restaurant.setId(id);
                return restaurant;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM restaurants";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                Float rating = resultSet.getFloat("rating");
                Restaurant restaurant = new Restaurant(name, address, description, rating);
                restaurant.setId(id);
                restaurants.add(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
