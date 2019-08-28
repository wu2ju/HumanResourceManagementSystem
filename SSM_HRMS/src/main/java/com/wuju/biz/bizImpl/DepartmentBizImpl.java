package com.wuju.biz.bizImpl;

import com.wuju.biz.DepartmentBiz;
import com.wuju.biz.PositionBiz;
import com.wuju.dao.CompanyDao;
import com.wuju.dao.DepartmentDao;
import com.wuju.dao.PositionDao;
import com.wuju.model.Company;
import com.wuju.model.Department;
import com.wuju.model.Employee;
import com.wuju.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DepartmentBizImpl implements DepartmentBiz {
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private PositionDao positionDao;
    @Resource
    private PositionBiz positionBiz;
    @Resource
    private CompanyDao companyDao;

    @Override
    public boolean addDepartment(Department d) {
        //部门新增要进行重名判断；
        Department department = departmentDao.getDepartmentByDpName(d.getDpName());
        if (department != null){
            return false;
        }
        Company company = new Company(1);
        d.setCompany(company);
        Date date = new Date();
        java.sql.Date dpEstablish = new java.sql.Date(date.getTime());
        d.setDpEstablish(dpEstablish);
        departmentDao.addDepartment(d);
        return true;
    }

    @Override
    public boolean updateDepartment(Department d) {
        //只更改部门的名字，要进行重名判断；
        /*Department department = departmentDao.getDepartmentByDpName(d.getDpName());
        if (department != null){
            return false;
        }*/
        departmentDao.updateDepartment(d);
        return true;
    }

    @Override
    public boolean delDepartment(int dpId) {
        //若该部门下存在在职员工，不能进行删除；如果是一个空的部门，则连同职位一起删除；
        Department d = new Department(dpId);
        Department department = departmentDao.getDepartment(d);
        //Department中包含Company和Position
        if (department == null){
            // 表明部门中没有职位
            return true;
        }
        List<Position> positions = department.getPositions();
        for (Position position : positions) {
            boolean flag = positionBiz.delPosition(position.getpId());
            if (!flag){
                // 该部门下的职位不能删除 flag=false，则该部门不能删除
                return false;
            }
            /*HashMap<String,Integer> map = new HashMap<>();
            map.put("pId",position.getpId());
            map.put("eState",0); // 员工状态为0，表示员工在职
            Position p = positionDao.getPosition(map);
            //此Position的对象p中包含Department和eState=0的Employee
            List<Employee> employees = p.getEmployees();
            if (employees != null && employees.size() > 0){
                return false; // 表明这个部门的职位有员工，此部门不能删除
            }*/
        }
        departmentDao.delDepartment(dpId);
        return true;
    }

    @Override
    public Department getDepartment(Department d) {
//        可以通过查看某部门，可以查看该部门下的所有职位和员工；
        Department department = departmentDao.getDepartment(d);
        //Department中包含Company和Position
        // 如果部门中没有职位，那么department为null，
        if (department == null){
            return null;
            /*if (d.getDpId() != null){
                return departmentDao.getDepartmentById(d.getDpId());
            }
            if (d.getDpName() != null){
                return departmentDao.getDepartmentByDpName(d.getDpName());
            }*/
        }
        List<Position> positions = department.getPositions();
        // 此职位中的员工集合为null,需要继续查找职位
        List<Position> ps = new ArrayList<>();
        for (Position position : positions) {
            HashMap<String,Integer> map = new HashMap<>();
            map.put("pId",position.getpId());
            map.put("eState",0); // 员工状态为0，表示员工在职
            Position p = positionDao.getPosition(map);
            // 职位没有在职员工则为null
            if (p == null){
                // 存在职位，但职位下没有在职员工，也要找出来
                p = positionDao.getPositionById(position.getpId());
            }
            ps.add(p);
        }
        department.setPositions(ps);
        return department;
    }

    @Override
    public Department getDepartmentById(int dpId) {
        return departmentDao.getDepartmentById(dpId);
    }

    @Override
    public Department getDepartmentByDpName(String dpName) {
        return departmentDao.getDepartmentByDpName(dpName);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}
