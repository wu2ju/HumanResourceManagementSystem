package com.wuju.controller;

import com.wuju.biz.DepartmentBiz;
import com.wuju.biz.EmployeeBiz;
import com.wuju.biz.PositionBiz;
import com.wuju.biz.RecruitBiz;
import com.wuju.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Resource
    private EmployeeBiz employeeBiz;
    @Resource
    private DepartmentBiz departmentBiz;
    @Resource
    private PositionBiz positionBiz;


    @RequestMapping("eEmployee")
    public String eEmployee(Integer pageNo, Position position, HttpSession session, Model model){
        // Ա����¼֮��鿴����Ա������Ϣ
        // 1����ePosition.jspҳ�洫��pId�����Ҹ�ְλ��Ա����
        // 2����eEmployee.jspҳ�洫��pName�����Ҹ�ְλ��Ա��
        // 3����������Ա��
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Employee> employeePage = new Page<>();
        if (position.getpId() == null && position.getpName() == null){
            employeePage = employeeBiz.getAllEmployeesByLimit(pageNo);
        }else {
            employeePage = employeeBiz.getEmployeeBypIdOrpNameAndLimit(position, pageNo);
        }
        model.addAttribute("employeePage",employeePage);
        return "eEmployee";
    }

    @RequestMapping("ePosition")
    public String ePosition(Integer pageNo, Department d,  Model model){
        // ������ڣ�1��d��Ϊnull��������������Ϊnull 2��d����ֻ��dpId 3��d����ֻ��dpName ���Ҹ�dpId/dpName�Ĳ���ְλ
        // Ա���鿴������Ϣ
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Position> positionPage = new Page<>();
        if (d.getDpId() == null && (d.getDpName() == null || d.getDpName().equals("") ) ){
            //select ѡ����ʱ��dpName����Ϊ""��
            positionPage = positionBiz.getAllPositionsByLimit(pageNo);
        }else {
            Department department = departmentBiz.getDepartment(d);
            //Department�а���Company��Position
            //Position�ְ���Employee
            if (department != null){
                positionPage = positionBiz.getPositionsByDpIdAndLimit(department.getDpId(),pageNo);
            }
        }
        model.addAttribute("positionPage",positionPage);
        return "ePosition";
    }

    @RequestMapping("eDepartment")
    public String eDepartment(Integer pageNo, Model model){
        // Ա���鿴������Ϣ
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Department> departmentPage = departmentBiz.getAllDepartmentsByLimit(pageNo);
        model.addAttribute("departmentPage",departmentPage);
        return "eDepartment";
    }


    @RequestMapping("employee")
    public String employee(HttpSession session, Model model){
        // Ա����¼֮��ֱ�ӿ����Լ�����Ϣ
        Employee e = (Employee) session.getAttribute("e");
        Employee employee = employeeBiz.getEmployeeById(e.geteId());
        // �����ְʱ����һ�����ˣ���Ա����״̬Ϊ0 �����ڣ���ô��Ա������ת����״̬Ϊ1��ת��ʱ��Ϊ��ǰʱ�䣩
        if (employee.geteState() == 0 && beforeOneMonth(employee.geteEntry())){
            employee.seteState(1);
            java.util.Date date = new java.util.Date();
            employee.seteBefull(new Date(date.getTime()));
            employeeBiz.updateEmployee(employee);
        }
        model.addAttribute("employee",employee);
        return "employee";
    }

    public static boolean beforeOneMonth(Date eEntry) {
        // ��ְʱ���ǵ�ǰʱ���ǰһ���µ�ͬһ��
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
        java.util.Date date = new java.util.Date();
        int yearEntry = Integer.parseInt(sdf2.format(eEntry));
        int monthEntry = Integer.parseInt(sdf.format(eEntry));
        int dayEntry = Integer.parseInt(sdf1.format(eEntry));
        int yearNow = Integer.parseInt(sdf2.format(date));
        int monthNow = Integer.parseInt(sdf.format(date));
        int dayNow = Integer.parseInt(sdf1.format(date));
        if (dayEntry == dayNow){
            if (yearNow == yearEntry){
                //ͬһ��
                if (monthNow-monthEntry==1){
                    return true;
                }
            }else if (yearNow-yearEntry == 1){
                //���һ��
                if (monthNow-monthEntry==-11){
                    return true;
                }
            }
        }
        return false;
    }
}
