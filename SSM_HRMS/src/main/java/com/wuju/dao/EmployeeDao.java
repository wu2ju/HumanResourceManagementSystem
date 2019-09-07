package com.wuju.dao;

import com.wuju.model.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeDao {
    Employee getEmployeeByAccountAndPassword(Employee e);
    Employee getEmployee(Employee e);
    Employee getEmployeeByeId(int eId);
    Employee getEmployeeById(int eId);

    // ���������е����⣬Ӧ������ѵ��˴���Ҫ�ǻ��Ա������ѵ���õ�employee.getTrains()
    Employee getEmployeeByeIdAndToStateAndTrState(HashMap map);
    // �ҳ�����trState=2�˵���ѵ���棬�շ���toState=1����ѵ���鿴��eIdԱ������ѵ

    // ��������ע��
    Employee getEmployeeByeIdAndTrState(HashMap map);
    // �ҳ�����trState=2�˵���ѵ���棬�ҵ�eIdԱ������ѵ��¼
    Employee getEmployeeByeAccount(String eAccount);
    List<Employee> getEmployeeBypId(int pId);
    List<Employee> getAllEmployees();
    // ��ȡ�ǹ���Ա������Ա��
    boolean addEmployee(Employee e);
    boolean updateEmployee(Employee e);

    List<Employee> getAllEmployeesByLimit(HashMap map);
    int getAllEmployeesCount();
    List<Employee> getEmployeeBypIdAndLimit(HashMap map);
    int getEmployeeCountBypId(int pId);
}
