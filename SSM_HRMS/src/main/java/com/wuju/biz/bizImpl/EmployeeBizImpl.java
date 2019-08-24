package com.wuju.biz.bizImpl;

import com.wuju.biz.EmployeeBiz;
import com.wuju.dao.EmployeeDao;
import com.wuju.model.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeBizImpl implements EmployeeBiz {
    @Resource
    private EmployeeDao employeeDao;

    public Employee login(Employee e) {
        if (e == null){
            return null;
        }
        return employeeDao.getEmployeeByAccountAndPassword(e);
    }
}
