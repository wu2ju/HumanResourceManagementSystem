package com.wuju.dao;

import com.wuju.model.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee getEmployeeByAccountAndPassword(Employee e);
    Employee getEmployee(Employee e);
    List<Employee> getAllEmployees();
}
