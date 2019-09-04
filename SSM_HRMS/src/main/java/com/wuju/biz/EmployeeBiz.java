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
    // ����ְλ�����Ҹ�ְλ�µ�Ա��
    List<Employee> getEmployeeBydpName(String dpName);
    // ���ݲ��������Ҹò��ŵ�ְλ������ְλ�µ�Ա��
    Employee getEmployeeByeAccount(String eAccount);
    Employee getEmployeeById(int eId);

    Employee getEmployeeByeIdAndToStateAndTrState(int eId, int toState, int trState);
    // �ҳ������˵���ѵ���棬�շ�������ѵ���鿴��Ա����
    Employee getEmployeeByeIdAndTrState(int eId, int trState);
    // �ҳ������˵���ѵ���棬�ҵ�Ա������ѵ��¼
    boolean addEmployee(Employee e);
    boolean updateEmployee(Employee e);

    Page<Employee> getAllEmployeesByLimit(int pageNo);
    Page<Employee> getEmployeeBypIdOrpNameAndLimit(Position position, int pageNo);
}
