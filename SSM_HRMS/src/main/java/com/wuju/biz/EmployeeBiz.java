package com.wuju.biz;

import com.wuju.model.Employee;
import com.wuju.model.Page;
import com.wuju.model.Position;

import java.util.List;

public interface EmployeeBiz {
    Employee login(Employee e);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeeBypId(int pId);
    Employee getEmployeeById(int eId);
    boolean addEmployee(Employee e);
    boolean updateEmployee(Employee e);

    Page<Employee> getAllEmployeesByLimit(int pageNo);
    Page<Employee> getEmployeeBypIdOrpNameAndLimit(Position position, int pageNo);
}
