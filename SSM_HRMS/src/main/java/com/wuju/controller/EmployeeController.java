package com.wuju.controller;

import com.wuju.biz.*;
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
    @Resource
    private TrainObjectBiz trainObjectBiz;
    @Resource
    private TrainBiz trainBiz;


    @RequestMapping("eTrain")
    public String eTrain(Integer pageNo, Integer eId, HttpSession session, Model model){
        // 员工登录之后查看员工的培训通知
        Employee e = (Employee) session.getAttribute("e");
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Train> trainPage = new Page<>();
        // 员工
        if (e.geteType() == 1){
            Employee employeeWithTrain = (Employee) session.getAttribute("employeeWithTrain");
            //培训通知被点击之后，就不许再出现
            if (employeeWithTrain != null){
                if (e.geteId() == employeeWithTrain.geteId()){
                    List<Train> trains = employeeWithTrain.getTrains();
                    if (trains != null && trains.size() > 0) {
                        for (Train train : trains) {
                            TrainObject trainObject = trainObjectBiz.getTrainObjectByeIdAndTrIdAndToState(e.geteId(), train.getTrId(),1);
                            if (trainObject != null){
                                trainObject.setToState(2);
                                trainObjectBiz.updateTrainObjectToState(trainObject);
                                //更新培训通知不出现
                                session.removeAttribute("employeeWithTrain");
                            }
                        }
                    }
                }
            }
            // 显示到eTrain.jsp的数据
            trainPage = trainBiz.getTrainByeIdAndTrStateAndLimit(e.geteId(), 2, pageNo);
        }else {
            // 管理员要么查看所有的培训记录，或者某个员工的培训记录
            if (eId == null){
                trainPage = trainBiz.getAllTrainsByLimit(pageNo);
            }else {
                trainPage = trainBiz.getTrainByeIdAndLimit(eId, pageNo);
            }
            List<Employee> employees = employeeBiz.getAllEmployees();
            model.addAttribute("employees",employees);
        }
        model.addAttribute("trainPage",trainPage);

        /*Employee employeeWithTrain1 = employeeBiz.getEmployeeByeIdAndTrState(e.geteId(), 2);
        // 查看到该员工已经发布的培训
        List<Train> trains = employeeWithTrain1.getTrains();
        if (trains != null && trains.size() > 0){
            for (Train train : trains) {
                // 把培训对象放进去
                train.setEmployees(trainBiz.getTrainById(train.getTrId()).getEmployees());
            }
            model.addAttribute("trains",trains);
        }*/
        return "eTrain";
    }

    @RequestMapping("eEmployee")
    public String eEmployee(Integer pageNo, Position position, HttpSession session, Model model){
        // 员工登录之后查看所有员工的信息
        // 1、从ePosition.jsp页面传来pId，查找该职位的员工，
        // 2、从eEmployee.jsp页面传来pName，查找该职位的员工
        // 3、查找所有员工
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Employee> employeePage = new Page<>();
        System.out.println("eEmployee: " + position);
        if (position == null || position.getpId() == null && (position.getpName() == null || position.getpName().equals("null"))){
            employeePage = employeeBiz.getAllEmployeesByLimit(pageNo);
        }else {
            employeePage = employeeBiz.getEmployeeBypIdOrpNameAndLimit(position, pageNo);
        }
        System.out.println("eEmployee: " + employeePage);
        model.addAttribute("employeePage",employeePage);
        return "eEmployee";
    }

    @RequestMapping("ePosition")
    public String ePosition(Integer pageNo, Department d,  Model model){
        // 三个入口，1、d不为null，但是所有属性为null 2、d里面只有dpId 3、d里面只有dpName 查找该dpId/dpName的部门职位
        // 员工查看部门信息
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Position> positionPage = new Page<>();
        if (d.getDpId() == null && (d.getDpName() == null || d.getDpName().equals("") ) ){
            //select 选择部门时，dpName可能为""。
            positionPage = positionBiz.getAllPositionsByLimit(pageNo);
        }else {
            Department department = departmentBiz.getDepartment(d);
            //Department中包含Company和Position
            //Position又包含Employee
            if (department != null){
                positionPage = positionBiz.getPositionsByDpIdAndLimit(department.getDpId(),pageNo);
            }
        }
        model.addAttribute("positionPage",positionPage);
        return "ePosition";
    }

    @RequestMapping("eDepartment")
    public String eDepartment(Integer pageNo, Model model){
        // 员工查看部门信息
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Department> departmentPage = departmentBiz.getAllDepartmentsByLimit(pageNo);
        model.addAttribute("departmentPage",departmentPage);
        return "eDepartment";
    }


    @RequestMapping("employee")
    public String employee(HttpSession session, Model model){
        // 员工登录之后直接看到自己的信息
        Employee e = (Employee) session.getAttribute("e");
        Employee employee = employeeBiz.getEmployeeById(e.geteId());
        // 如果入职时间有一个月了，且员工的状态为0 试用期，那么给员工进行转正（状态为1，转正时间为当前时间）
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
        // 入职时间是当前时间的前一个月的同一天
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
                //同一年
                if (monthNow-monthEntry==1){
                    return true;
                }
            }else if (yearNow-yearEntry == 1){
                //相差一年
                if (monthNow-monthEntry==-11){
                    return true;
                }
            }
        }
        return false;
    }
}
