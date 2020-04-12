package com.github.lbovolini.servlet.controller;

import com.github.lbovolini.servlet.dao.UserDao;
import com.github.lbovolini.servlet.model.User;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserController {

    UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    public void create(User user) {
        userDao.create(user);
    }

}
