
package com.example.dinnerreserver.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements IUserDAO {
    private Connection connection;

    public SqliteUserDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void insertSampleData() {
        try {
            Statement checkStatement = connection.createStatement();
            ResultSet resultSet = checkStatement.executeQuery("SELECT COUNT(*) FROM users");
            if (resultSet.next() && resultSet.getInt(1) == 0) {
                // Only insert sample data if the table is empty
                Statement insertStatement = connection.createStatement();
                String insertQuery = "INSERT INTO users (username, password, email) VALUES "
                        + "('John', 'Doe', '1234', 'johndoe@example.com'),"
                        + "('Jane', 'Doe', '1234', 'janedoe@example.com'),"
                        + "('Jay', 'Doe', '1234', 'jaydoe@example.com')";
                insertStatement.execute(insertQuery);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password, email) VALUES (?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(username, email, password);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                User user = new User(username, email, password);
                user.setId(id);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                User user = new User(username, email, password);
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
