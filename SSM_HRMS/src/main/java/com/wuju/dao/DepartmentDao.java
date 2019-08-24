package com.wuju.dao;

import com.wuju.model.Department;

public interface DepartmentDao {
    boolean addDepartment(Department d);
    boolean updateDepartment(Department d);
    boolean delDepartment(Department d);
    boolean getDepartment(Department d);
}
