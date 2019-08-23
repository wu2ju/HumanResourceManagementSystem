package com.wuju.dao;

import com.wuju.model.Admin;

public interface AdminDao {
    Admin getAdminByAccountAndPassword(Admin admin);
}
