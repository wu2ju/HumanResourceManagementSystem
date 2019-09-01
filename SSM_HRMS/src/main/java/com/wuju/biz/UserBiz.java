package com.wuju.biz;

import com.wuju.model.Employee;
import com.wuju.model.User;

import java.util.List;

public interface UserBiz {
    User login(User u);
    User getUserByuName(String uName);
    boolean register(User u);
    List<User> getAllUsers();
}
