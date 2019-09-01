package com.wuju.biz.bizImpl;

import com.wuju.biz.UserBiz;
import com.wuju.dao.UserDao;
import com.wuju.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UserBizImpl implements UserBiz {
    @Resource
    private UserDao userDao;

    @Override
    public User login(User u) {
        if (u == null){
            return null;
        }
        return userDao.getUserByNameAndPass(u);
    }

    @Override
    public User getUserByuName(String uName) {
        return userDao.getUserByuName(uName);
    }

    @Override
    public boolean register(User u) {
        // 注册，如果用户名已经存在，那么不能注册
        if (u == null){
            return false;
        }
        /*User user = userDao.getUserByuName(u.getuName());
        if (user != null){
            return false;
        }*/
        userDao.addUser(u);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
