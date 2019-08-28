package com.wuju.biz;

import com.wuju.model.Employee;

import java.util.List;

public interface EmployeeBiz {
    Employee login(Employee e);
    List<Employee> getAllEmployees();
}
