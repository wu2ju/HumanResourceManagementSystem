package com.wuju.dao;

import com.wuju.model.Employee;
import com.wuju.model.User;

import java.util.List;

public interface UserDao {
    boolean addUser(User u);
    User getUserByNameAndPass(User u);
    User getUserByuName(String uName);
    User getUserById(int uId);
    User getUser(User u);
    List<User> getAllUsers();
}
