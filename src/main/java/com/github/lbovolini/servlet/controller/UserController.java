package com.github.lbovolini.servlet.controller;

import com.github.lbovolini.servlet.dao.UserDao;
import com.github.lbovolini.servlet.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class UserController {

    UserDao userDao;

    private boolean orderAsc = true;

    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public UserController() {
        userDao = new UserDao();
    }

    public String create(User user) {
        try {
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "index.xhtml?faces-redirect=true";
    }

    public String update(User user) {
        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "index.xhtml?faces-redirect=true";
    }

    public void delete(int id) {
        try {
            userDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/*    public String submit() {
        //HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //String nome = request.getParameter("nomeDoFormulario:nomeDoCampo");
        User user = userDao.find("lucasbovolini@hotmail.com");
        this.user = user;
        return "edit.xhtml";
    }*/


    public List findAll(int column) {
        List userList = null;

        try {
            userList = userDao.findAll();

            System.out.println(orderAsc);
            userList.sort(new Comparator<User>() {
                @Override
                public int compare(User u1, User u2) {
                    if (column == 0) {
                        if (orderAsc) {
                            return u1.getFirstName().toLowerCase().compareTo(u2.getFirstName().toLowerCase());
                        } else {
                            return u2.getFirstName().toLowerCase().compareTo(u1.getFirstName().toLowerCase());
                        }
                    }
                    if (column == 1) {
                        return u1.getFirstName().toLowerCase().compareTo(u2.getLastName().toLowerCase());
                    }
                    if (column == 2) {

                    }
                    if (column == 3) {

                    }
                    if (column == 4) {

                    }
                    return u1.getFirstName().compareTo(u2.getFirstName());
                }
            });
            orderAsc = !orderAsc;

            userList.forEach(e -> System.out.println(((User)e).getFirstName()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public String edit(int id) {
        User u = null;

        try {
            u = userDao.find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sessionMap.put("user", u);

        return "edit.xhtml?faces-redirect=true";
    }

}
