package com.wuju.biz;

import com.wuju.model.Employee;
import com.wuju.model.Page;
import com.wuju.model.Position;

import java.util.HashMap;
import java.util.List;

public interface EmployeeBiz {
    Employee login(Employee e);
    List<Employee> getAllEmployees();
    List<Employee> getEmployeeBypId(int pId);
    List<Employee> getEmployeeBypName(String pName);
    // 根据职位名称找该职位下的员工
    List<Employee> getEmployeeBydpName(String dpName);
    // 根据部门名称找该部门的职位，再找职位下的员工
    Employee getEmployeeByeAccount(String eAccount);
    Employee getEmployeeById(int eId);

    Employee getEmployeeByeIdAndToStateAndTrState(int eId, int toState, int trState);
    // 找出发布了的培训里面，刚发布的培训，查看该员工的
    Employee getEmployeeByeIdAndTrState(int eId, int trState);
    // 找出发布了的培训里面，找到员工的培训记录
    boolean addEmployee(Employee e);
    boolean updateEmployee(Employee e);

    Page<Employee> getAllEmployeesByLimit(int pageNo);
    Page<Employee> getEmployeeBypIdOrpNameAndLimit(Position position, int pageNo);
}
