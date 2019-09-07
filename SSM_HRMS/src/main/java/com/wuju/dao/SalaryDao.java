package com.wuju.dao;

import com.wuju.model.Salary;

import java.util.HashMap;
import java.util.List;

public interface SalaryDao {
    boolean addSalary(Salary sl);
    boolean updateSalary(int slId);
//    boolean delSalary(int slId);
    Salary getSalaryById(int slId);
    List<Salary> getSalaryByeIdAndLimit(HashMap map);
    int getSalaryByMonth();
    int getSalaryCountByeId(int eId);
    List<Salary> getAllSalarysByLimit(HashMap map);
    int getAllSalaryCount();
}
