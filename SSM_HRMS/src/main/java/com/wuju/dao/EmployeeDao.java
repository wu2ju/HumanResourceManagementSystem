package com.wuju.dao;

import com.wuju.model.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeDao {
    Employee getEmployeeByAccountAndPassword(Employee e);
    Employee getEmployee(Employee e);
    Employee getEmployeeByeId(int eId);
    List<Employee> getEmployeeBypId(int pId);
    List<Employee> getAllEmployees();
    boolean addEmployee(Employee e);
    boolean updateEmployee(Employee e);

    List<Employee> getAllEmployeesByLimit(HashMap map);
    int getAllEmployeesCount();
    List<Employee> getEmployeeBypIdAndLimit(HashMap map);
    int getEmployeeCountBypId(int pId);
}
