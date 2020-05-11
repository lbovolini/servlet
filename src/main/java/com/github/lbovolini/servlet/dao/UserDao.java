package com.github.lbovolini.servlet.dao;

import com.github.lbovolini.servlet.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false", "lbovolini", "lbovolini");
    }

    public void create(User user) throws SQLException {

        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = connect()) {

            Date date = new Date(user.getBirthday().getTime());

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setDate(6, date);

            preparedStatement.executeUpdate();
        }
    }

    public void update(User user) throws SQLException {

        String sql = "UPDATE user " +
                "SET first_name = ?, " +
                    " last_name = ?, " +
                    "     email = ?, " +
                    "     phone = ?, " +
                    "  birthday = ? " +
                "WHERE id = ?;";

        try (Connection connection = connect()) {

            Date date = new Date(user.getBirthday().getTime());

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setDate(5, date);
            preparedStatement.setInt(6, user.getId());

            preparedStatement.executeUpdate();
        }
    }

    public User find(String email) throws SQLException {

        String sql = "SELECT * FROM user WHERE email = ?;";
        User user = new User();

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setBirthday(resultSet.getDate("birthday"));
        }

        return user;
    }

    // Used to fetch all records
    public List findAll() throws SQLException {

        List usersList = new ArrayList();
        String sql = "SELECT * FROM user";

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setBirthday(resultSet.getDate("birthday"));

                usersList.add(user);
            }
        }

        return usersList;
    }

    // Used to fetch record to update
    public User find(int id) throws SQLException {
        User user = null;

        String sql = "SELECT * FROM user WHERE id = " + id + " ;";

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            user.setBirthday(resultSet.getDate("birthday"));
        }

        return user;
    }

    public void delete(int id) throws SQLException {

        String sql = "DELETE FROM user WHERE id = ?;";

        try (Connection connection = connect()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }
    }
}
