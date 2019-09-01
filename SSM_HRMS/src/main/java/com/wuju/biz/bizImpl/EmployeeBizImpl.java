package com.wuju.biz.bizImpl;

import com.wuju.biz.EmployeeBiz;
import com.wuju.dao.EmployeeDao;
import com.wuju.dao.PositionDao;
import com.wuju.model.Employee;
import com.wuju.model.Page;
import com.wuju.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeBizImpl implements EmployeeBiz {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private PositionDao positionDao;

    public Employee login(Employee e) {
        if (e == null){
            return null;
        }
        return employeeDao.getEmployeeByAccountAndPassword(e);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public List<Employee> getEmployeeBypId(int pId) {
        return employeeDao.getEmployeeBypId(pId);
    }

    @Override
    public Employee getEmployeeById(int eId) {
        return employeeDao.getEmployee(new Employee(eId));
    }

    @Override
    public boolean addEmployee(Employee e) {
        return employeeDao.addEmployee(e);
    }

    @Override
    public boolean updateEmployee(Employee e) {
        return employeeDao.updateEmployee(e);
    }

    @Override
    public Page<Employee> getAllEmployeesByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = employeeDao.getAllEmployeesCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Employee> employees = employeeDao.getAllEmployeesByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(employees);
        return page;
    }

    @Override
    public Page<Employee> getEmployeeBypIdOrpNameAndLimit(Position position, int pageNo) {
        Page page=new Page<>();
        Integer pId = null;
        if (position.getpId() != null){
            pId = position.getpId();
        }else {
            Position position1 = positionDao.getPositionByPName(position.getpName());
            pId = position1.getpId();
        }
        int totalRows = employeeDao.getEmployeeCountBypId(pId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("pId",pId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Employee> employees = employeeDao.getEmployeeBypIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(employees);
        return page;
    }
}
