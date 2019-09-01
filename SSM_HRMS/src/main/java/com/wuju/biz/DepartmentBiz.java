package com.wuju.biz;

import com.wuju.model.Department;
import com.wuju.model.Page;

import java.util.List;

public interface DepartmentBiz {
    boolean addDepartment(Department d);
    boolean updateDepartment(Department d);
    boolean delDepartment(int dpId);
    Department getDepartment(Department d);
    Department getDepartmentById(int dpId);
    Department getDepartmentByDpName(String dpName);
    List<Department> getAllDepartments();
    Page<Department> getAllDepartmentsByLimit(int pageNo);
}
