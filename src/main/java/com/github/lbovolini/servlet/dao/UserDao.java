package com.github.lbovolini.servlet.dao;

import com.github.lbovolini.servlet.model.User;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDao {

    public void create(User user) {

        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc?useSSL=false", "lbovolini", "lbovolini")) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date(simpleDateFormat.parse(user.getBirthday()).getTime());

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setDate(6, date);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
