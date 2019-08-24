package com.wuju.dao;

import com.wuju.model.Employee;

public interface EmployeeDao {
    Employee getEmployeeByAccountAndPassword(Employee e);
}
