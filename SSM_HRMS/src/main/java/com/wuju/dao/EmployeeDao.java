package com.wuju.dao;

import com.wuju.model.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeDao {
    Employee getEmployeeByAccountAndPassword(Employee e);
    Employee getEmployee(Employee e);
    Employee getEmployeeByeId(int eId);
    Employee getEmployeeById(int eId);

    // 下面两个有点问题，应该在培训里，此处主要是获得员工的培训，用的employee.getTrains()
    Employee getEmployeeByeIdAndToStateAndTrState(HashMap map);
    // 找出发布trState=2了的培训里面，刚发布toState=1的培训，查看该eId员工的培训

    // 下面两个注销
    Employee getEmployeeByeIdAndTrState(HashMap map);
    // 找出发布trState=2了的培训里面，找到eId员工的培训记录
    Employee getEmployeeByeAccount(String eAccount);
    List<Employee> getEmployeeBypId(int pId);
    List<Employee> getAllEmployees();
    // 获取非管理员的所有员工
    boolean addEmployee(Employee e);
    boolean updateEmployee(Employee e);

    List<Employee> getAllEmployeesByLimit(HashMap map);
    int getAllEmployeesCount();
    List<Employee> getEmployeeBypIdAndLimit(HashMap map);
    int getEmployeeCountBypId(int pId);
}
