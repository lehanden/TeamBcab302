package com.example.dinnerreserver.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The database access object for the restaurants table
 */
public class SqliteRestaurantDAO implements IRestaurantDAO {

    private Connection connection;

    /**
     * Constructor method for the database connection and table
     */
    public SqliteRestaurantDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
        insertSampleData();
    }

    protected void createTable() {
        try {
            Statement delete = connection.createStatement();
            String deleteQuery = "DROP TABLE IF EXISTS restaurants";
            delete.execute(deleteQuery);
            Statement create = connection.createStatement();
            String createQuery = "CREATE TABLE IF NOT EXISTS restaurants ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "address VARCHAR NOT NULL,"
                    + "description VARCHAR NOT NULL,"
                    + "rating FLOAT NOT NULL,"
                    + "imageSource VARCHAR NOT NULL,"
                    + "menuSource VARCHAR NOT NULL"
                    + ")";
            create.execute(createQuery);
        } catch (Exception e) {
            if (!Objects.equals(e.getMessage(), "[SQLITE_LOCKED]  A table in the database is locked (database table is locked)")) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inserts restaurant data into the database table
     */
    public void insertSampleData() {
        try {
            Statement checkStatement = connection.createStatement();
            ResultSet resultSet = checkStatement.executeQuery("SELECT COUNT(*) FROM restaurants");
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                // Only insert sample data if the table is empty
                Statement insertStatement = connection.createStatement();
                String insertQuery = "INSERT INTO restaurants (name, address, description, rating, imageSource, menuSource) VALUES "
                    + "('San Kai Japanese Restaurant', " +
                        "'164 Grey St, South Brisbane', " +
                        "'Japanese classics like sushi, tempura & gyoza served in a chic dining room with sidewalk seating.', " +
                        "3.0," +
                        "'Restaurant1.jpg'," +
                        "'https://quandoo-assets-partner.s3-eu-west-1.amazonaws.com/partner/uploads/d52bde99-b012-4cf1-ade2-3a243296e54b/MD-document-ad724adf-6a9b-47af-9ec0-4a579a1f3abd.pdf'),"
                    + "('Olé Restaurant', " +
                        "'Shop/B12 Little Stanley St, South Brisbane', " +
                        "'Vibrant Spanish eatery with tapas, sangria bar and stylish decor, plus an intricate wooden ceiling.', " +
                        "4.2," +
                        "'Restaurant2.jpeg'," +
                        "'https://olerestaurant.com.au/menu/'),"
                    + "('Geláre South Bank', " +
                        "'3/164 Grey St, South Brisbane QLD 4101', " +
                        "'Ice cream, smoothies and low-fat frozen yoghurt, plus classic breakfasts, in a bright cafe chain.', " +
                        "3.9," +
                        "'Restaurant3.jpg'," +
                        "'https://gelare.com.au/menu/'),"
                    + "('The Spaghetti House Trattoria'," +
                        "'Little Stanley St &, Grey St'," +
                        "'Familiar Italian dishes served in a stylish, wood-paneled space with big windows & a patio.'," +
                        "4.0," +
                        "'Restaurant4.jpg'," +
                        "'http://www.spaghettihouse.com.au/menu/'),"
                    + "('Sage Yellowfin'," +
                        "'164 Little Stanley St'," +
                        "'Sage Yellowfin, we want you to explore diverse cuisine from around the world. Whether you are a fan of exotic spices or international delicacies, our curated menu caters for every palate.'," +
                        "4.3," +
                        "'Restaurant5.jpg'," +
                        "'https://yellowfinrestaurant.com.au/southbank/menu-pdf/'),"
                    + "('Ahmet''s Turkish Restaurant'," +
                        "'Shop 10/168 Grey St'," +
                        "'Bright Turkish restaurant filled with rugs and lanterns, serving feasting dishes to share.'," +
                        "4.3," +
                        "'Restaurant6.jpg'," +
                        "'https://ahmets.com/menus/'),"
                    + "('Denim Co.'," +
                        "'Shop/9A Little Stanley St'," +
                        "'Denim Co. is the answer to every coffee addict’s prayers. This iconic cafe is tucked away on South Bank''s teeming ‘Little Stanley St’, housing a selection of irresistible sweet treats alongside tasty all day breakfast (until 3pm) and delicious lunch to late evening dining options.'," +
                        "4.0," +
                        "'Restaurant7.jpg'," +
                        "'https://www.denimcocafe.com/#denimco'),"
                    + "('Barbossa Bar & Bottle Shop'," +
                        "'7/166 Grey St'," +
                        "'Here at Barbossa Bar & Bottle Shop, we have built an experience as vibrant as the Caribbean.'," +
                        "4.2," +
                        "'Restaurant8.jpg'," +
                        "'https://www.barbossabar.com.au/_files/ugd/06e752_8052fb1a2634463791ba52fe80cad69b.pdf'),"
                    + "('San Churro South Bank'," +
                        "'Shop 2/161 Grey St'," +
                        "'Vibrant chain cafe offering churros & decadent desserts, plus organic, fair-trade coffee.'," +
                        "3.7," +
                        "'Restaurant9.jpg'," +
                        "'https://www.sanchurro.com/'),"
                    + "('Ovello Bar and Kitchen'," +
                        "'161 Grey St'," +
                        "'Ovello Bar & Kitchen is inspired by relaxed Mediterranean holidays, sun-soaked terraces, lush gardens, colourful parasols and the casual vibes of the Amalfi Coast.'," +
                        "3.4," +
                        "'Restaurant10.jpg'," +
                        "'https://www.ovello.com.au/menus/')";
                insertStatement.execute(insertQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRestaurant(Restaurant restaurants) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO restaurants (name, address, description, rating, imageSource, menuSource) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, restaurants.getName());
            statement.setString(2, restaurants.getAddress());
            statement.setString(3, restaurants.getDescription());
            statement.setString(4, restaurants.getRating().toString());
            statement.setString(5, restaurants.getImageSource());
            statement.setString(6, restaurants.getMenuSource());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                restaurants.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(Restaurant restaurants) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM restaurants WHERE id = ?");
            statement.setInt(1, restaurants.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE restaurants SET name = ?, address = ?, description = ?, rating = ?, imageSource = ?, menuSource = ? WHERE id = ?");
            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getAddress());
            statement.setString(3, restaurant.getDescription());
            statement.setFloat(4, restaurant.getRating());
            statement.setString(5, restaurant.getImageSource());
            statement.setString(6, restaurant.getMenuSource());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurant(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM restaurants WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                Float rating = resultSet.getFloat("rating");
                String imageSource = resultSet.getString("imageSource");
                String menuSource = resultSet.getString("menuSource");
                Restaurant restaurant = new Restaurant(id, name, address, description, rating, imageSource, menuSource);
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
                String imageSource = resultSet.getString("imageSource");
                String menuSource = resultSet.getString("menuSource");
                Restaurant restaurant = new Restaurant(id, name, address, description, rating, imageSource, menuSource);
                restaurant.setId(id);
                restaurants.add(restaurant);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }
}
