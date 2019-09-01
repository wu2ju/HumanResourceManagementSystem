package com.wuju.dao;

import com.wuju.model.Department;

import java.util.HashMap;
import java.util.List;

public interface DepartmentDao {
    boolean addDepartment(Department d);
    boolean updateDepartment(Department d);
    boolean delDepartment(int dpId);
    Department getDepartment(Department d);
    Department getDepartmentById(int dpId);
    Department getDepartmentByDpName(String dpName);
    List<Department> getAllDepartments();
    List<Department> getAllDepartmentsByLimit(HashMap map);
    int getAllDepartmentsCount();
}
