package com.github.lbovolini.servlet.controller;

import com.github.lbovolini.servlet.dao.UserDao;
import com.github.lbovolini.servlet.model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class UserServlet extends HttpServlet {

    UserDao userDao;

    private boolean firstNameOrderAsc = true;
    private boolean lastNameOrderAsc = true;
    private boolean emailOrderAsc = true;
    private boolean phoneOrderAsc = true;
    private boolean birthdayOrderAsc = true;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String index = req.getParameter("index");
        List<User> userList = findAll(Integer.parseInt(index));

        String json = new Gson().toJson(userList);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    public List findAll(int column) {
        List userList = null;

        try {
            userList = userDao.findAll();

            userList.sort(new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    if (column == 0) {
                        if (firstNameOrderAsc) {
                            return u1.getFirstName().toLowerCase().compareTo(u2.getFirstName().toLowerCase());
                        }
                        return u2.getFirstName().toLowerCase().compareTo(u1.getFirstName().toLowerCase());
                    }
                    if (column == 1) {
                        if (lastNameOrderAsc) {
                            return u1.getLastName().toLowerCase().compareTo(u2.getLastName().toLowerCase());
                        }
                        return u2.getLastName().toLowerCase().compareTo(u1.getLastName().toLowerCase());
                    }
                    if (column == 2) {
                        if (emailOrderAsc) {
                            return u1.getEmail().toLowerCase().compareTo(u2.getEmail().toLowerCase());
                        }
                        return u2.getEmail().toLowerCase().compareTo(u1.getEmail().toLowerCase());
                    }
                    if (column == 3) {
                        if (phoneOrderAsc) {
                            return u1.getPhone().toLowerCase().compareTo(u2.getPhone().toLowerCase());
                        }
                        return u2.getPhone().toLowerCase().compareTo(u1.getPhone().toLowerCase());
                    }
                    if (column == 4) {
                        if (birthdayOrderAsc) {
                            return u1.getBirthday().compareTo(u2.getBirthday());
                        }
                        return u2.getBirthday().compareTo(u1.getBirthday());
                    }
                    return u1.getFirstName().compareTo(u2.getFirstName());
                }
            });

            if (column == 0) {
                firstNameOrderAsc = !firstNameOrderAsc;
            } else if (column == 1) {
                lastNameOrderAsc = !lastNameOrderAsc;
            } else if (column == 2) {
                emailOrderAsc = !emailOrderAsc;
            } else if (column == 3) {
                phoneOrderAsc = !phoneOrderAsc;
            } else if (column == 4) {
                birthdayOrderAsc = !birthdayOrderAsc;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
