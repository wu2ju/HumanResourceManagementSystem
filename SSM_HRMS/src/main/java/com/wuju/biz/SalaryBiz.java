package com.wuju.biz;

import com.wuju.model.Page;
import com.wuju.model.Salary;

import java.util.HashMap;
import java.util.List;

public interface SalaryBiz {
    boolean addSalary();
    boolean updateSalary(int slId);
    Page<Salary> getSalaryByeIdAndLimit(int eId, int pageNo);
    Page<Salary> getAllSalarysByLimit(int pageNo);
}
