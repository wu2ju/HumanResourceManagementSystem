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
        //��������Ҫ���������жϣ�
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
        //ֻ���Ĳ��ŵ����֣�Ҫ���������жϣ�
        /*Department department = departmentDao.getDepartmentByDpName(d.getDpName());
        if (department != null){
            return false;
        }*/
        departmentDao.updateDepartment(d);
        return true;
    }

    @Override
    public boolean delDepartment(int dpId) {
        //���ò����´�����ְԱ�������ܽ���ɾ���������һ���յĲ��ţ�����ְͬλһ��ɾ����
        Department d = new Department(dpId);
        Department department = departmentDao.getDepartment(d);
        //Department�а���Company��Position
        if (department == null){
            // ����������û��ְλ
            return true;
        }
        List<Position> positions = department.getPositions();
        for (Position position : positions) {
            boolean flag = positionBiz.delPosition(position.getpId());
            if (!flag){
                // �ò����µ�ְλ����ɾ�� flag=false����ò��Ų���ɾ��
                return false;
            }
            /*HashMap<String,Integer> map = new HashMap<>();
            map.put("pId",position.getpId());
            map.put("eState",0); // Ա��״̬Ϊ0����ʾԱ����ְ
            Position p = positionDao.getPosition(map);
            //��Position�Ķ���p�а���Department��eState=0��Employee
            List<Employee> employees = p.getEmployees();
            if (employees != null && employees.size() > 0){
                return false; // ����������ŵ�ְλ��Ա�����˲��Ų���ɾ��
            }*/
        }
        departmentDao.delDepartment(dpId);
        return true;
    }

    @Override
    public Department getDepartment(Department d) {
//        ����ͨ���鿴ĳ���ţ����Բ鿴�ò����µ�����ְλ��Ա����
        Department department = departmentDao.getDepartment(d);
        //Department�а���Company��Position
        // ���������û��ְλ����ôdepartmentΪnull��
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
        // ��ְλ�е�Ա������Ϊnull,��Ҫ��������ְλ
        List<Position> ps = new ArrayList<>();
        for (Position position : positions) {
            HashMap<String,Integer> map = new HashMap<>();
            map.put("pId",position.getpId());
            map.put("eState",0); // Ա��״̬Ϊ0����ʾԱ����ְ
            Position p = positionDao.getPosition(map);
            // ְλû����ְԱ����Ϊnull
            if (p == null){
                // ����ְλ����ְλ��û����ְԱ����ҲҪ�ҳ���
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
