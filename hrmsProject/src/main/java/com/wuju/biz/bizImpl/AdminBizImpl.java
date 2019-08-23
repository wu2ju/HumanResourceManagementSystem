package com.wuju.biz.bizImpl;

import com.wuju.biz.AdminBiz;
import com.wuju.dao.AdminDao;
import com.wuju.model.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminBizImpl implements AdminBiz {
    @Resource
    private AdminDao adminDao;

    public Admin login(Admin admin) {
        if (admin == null){
            return null;
        }
        return adminDao.getAdminByAccountAndPassword(admin);
    }
}
