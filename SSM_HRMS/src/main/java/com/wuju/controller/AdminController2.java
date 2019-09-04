package com.wuju.controller;

import com.wuju.biz.*;
import com.wuju.model.*;
import com.wuju.util.ControllerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
/*
* 管理员的培训功能，
* */
@Controller
public class AdminController2 {
    @Resource
    private RecruitBiz recruitBiz;
    @Resource
    private DepartmentBiz departmentBiz;
    @Resource
    private PositionBiz positionBiz;
    @Resource
    private EmployeeBiz employeeBiz;
    @Resource
    private TrainBiz trainBiz;
    @Resource
    private TrainObjectBiz trainObjectBiz;
    @Resource
    private QuitBiz quitBiz;

    @RequestMapping("retireEmployee")
    @ResponseBody
    public String retireEmployee(String qtReason, String eId){
        // 根据pName部门名称，获取该部门下的所有职位的名称，调eId员工到pName职位
        System.out.println("changeEmployeePosition: " + qtReason + ", " + eId);
        if (qtReason == null || eId == null){
            return "false";
        }
        Employee employee = employeeBiz.getEmployeeById(Integer.parseInt(eId));
        if (employee == null){
            return "false";
        }
        java.util.Date date = new java.util.Date();
        Quit quit = new Quit(new Date(date.getTime()),qtReason,employee);
        quitBiz.addQuit(quit);
        //离职的同时，修改employee的eState=2
        employee.seteState(2);
        employeeBiz.updateEmployee(employee);
        return "success";
    }

    @RequestMapping("changeEmployeePosition")
    @ResponseBody
    public String changeEmployeePosition(String pName, String eId){
        // 根据pName部门名称，获取该部门下的所有职位的名称，调eId员工到pName职位
        System.out.println("changeEmployeePosition: " + pName + ", " + eId);
        if (pName == null || eId == null){
            return "false";
        }
        Employee employee = employeeBiz.getEmployeeById(Integer.parseInt(eId));
        Position position = positionBiz.getPositionByPName(pName);
        if (employee == null || position == null){
            return "false";
        }
        employee.setPosition(position);
        employeeBiz.updateEmployee(employee);
        return "success";
    }

    @RequestMapping("updateTrState")
    public String updateTrState(Train train){
        // train中有trId和trState，更新trState=2，还要更新发布时间

        Train train1 = trainBiz.getTrainById(train.getTrId());
        train1.setTrState(2);
        java.util.Date date = new java.util.Date();
        // 更新发布时间
        train1.setTrRelease(new Timestamp(date.getTime()));
        if (train1.getTrRelease().after(train1.getTrEnd())){
            // 结束时间已经过了，直接舍弃
            train1.setTrState(3);
        }
        trainBiz.updateTrain(train1);
        return "forward:aTrain";
    }

    @RequestMapping("delTrainObject")
    public String delTrainObject(Integer eId, Integer trId, Model model){
        TrainObject to = trainObjectBiz.getTrainObjectByeIdAndTrId(eId, trId);
        trainObjectBiz.delTrainObject(to.getToId());
        return "forward:aTrain";
    }

    @RequestMapping("updateTrain")
    public String updateTrain(Train train, String trBegin1, String trEnd1, Model model){
        // 修改培训的主题、内容、开始结束时间
        Train train1 = trainBiz.getTrainById(train.getTrId());
        Timestamp trBegin = ControllerUtil.strToTimestamp(trBegin1);
        Timestamp trEnd = ControllerUtil.strToTimestamp(trEnd1);
        train1.setTrBegin(trBegin);
        train1.setTrEnd(trEnd);
        train1.setTrTheme(train.getTrTheme());
        train1.setTrContent(train.getTrContent());
        boolean flag = trainBiz.updateTrain(train1);
        if (flag){
            model.addAttribute("str","修改成功");
        }else {
            model.addAttribute("str","开始结束时间设置有误，修改失败");
        }
        return "forward:aTrain";
    }

    @RequestMapping("delTrain")
    public String delTrain(Integer trId, Model model){
        if (trId == null){
            model.addAttribute("str","删除失败");
            return "forward:aTrain";
        }
        boolean flag = trainBiz.delTrain(trId);
        return "forward:aTrain";
    }

    @RequestMapping("addEmployeeToTrain")
    @ResponseBody
    public String addEmployeeToTrain(String eAccount, String trId){
        // 根据dpName部门名称，获取该部门下的所有职位的名称
        System.out.println("addEmployeeToTrain: " + eAccount + ", " + trId);
        if (eAccount == null || trId == null){
            return "false";
        }
        Employee employee = employeeBiz.getEmployeeByeAccount(eAccount);
        Train train = trainBiz.getTrainById(Integer.parseInt(trId));
        if (employee == null || train == null){
            return "false";
        }
        // 如果trId培训中有了eAccount员工，那么就不再添加该员工
        TrainObject trainObject = trainObjectBiz.getTrainObjectByeIdAndTrId(employee.geteId(), train.getTrId());
        if (trainObject != null){
            return "false";
        }
        trainObjectBiz.addTrainObject(new TrainObject(employee,train,1));
        return "success";
    }

    @RequestMapping("addTrain")
    public String addTrain(Train train, String trBegin1, String trEnd1, String dpName, String pName, String eAccount, Model model){
        if (train.getTrTheme() == null || train.getTrContent() == null || dpName == null || trBegin1 == null || trEnd1 == null){
            model.addAttribute("str","信息不完整，添加失败");
            return "forward:aTrain";
        }
        System.out.println("addTrain: " + train + ", " + trBegin1 + ", " + trEnd1);
        System.out.println("addTrain: " +  dpName + ", " + pName+ ", " +eAccount);
        List<Employee> employees = new ArrayList<>();
        if (eAccount != null && !eAccount.equals("")){
            // 培训对象选择了一个员工
            Employee employee = employeeBiz.getEmployeeByeAccount(eAccount);
            employees.add(employee);
        }else if (pName != null && !pName.equals("")){
            // 培训对象选择了一个职位下的员工
            employees.addAll(employeeBiz.getEmployeeBypName(pName));
        }else if (!dpName.equals("")){
            // 培训对象选择了一个部门下的员工
            employees.addAll(employeeBiz.getEmployeeBydpName(dpName));
        }
        train.setEmployees(employees);
        train.setTrRelease(new Timestamp(0));
        Timestamp trBegin = ControllerUtil.strToTimestamp(trBegin1);
        Timestamp trEnd = ControllerUtil.strToTimestamp(trEnd1);
        train.setTrBegin(trBegin);
        train.setTrEnd(trEnd);
        System.out.println("addTrain: " + train);
        boolean flag = trainBiz.addTrain(train);
        if (flag){
            model.addAttribute("str","添加成功");
        }else {
            model.addAttribute("str","开始结束时间设置有误，添加失败");
        }
        return "forward:aTrain";
    }

    @RequestMapping("aTrain")
    public String aTrain(Integer pageNo, Model model){
        // 显示部门信息，用来三级联动，选择职位
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Train> trainPage = trainBiz.getAllTrainsByLimit(pageNo);
        model.addAttribute("trainPage",trainPage);
        return "aTrain";
    }

    @RequestMapping("getEmployeeBypName")
    @ResponseBody
    public List<String> getEmployeeBypName(String pName){
        // 根据dpName部门名称，获取该部门下的所有职位的名称
        List<String> eAccounts = new ArrayList<>();
        System.out.println("getEmployeeBypName: " + pName);
        if (pName == null){
            return eAccounts;
        }
        List<Employee> employees = employeeBiz.getEmployeeBypName(pName);
        if (employees == null || employees.size() == 0){
            return eAccounts;
        }
        for (Employee e : employees) {
            eAccounts.add(e.geteAccount());
        }
        return eAccounts;
    }

    @RequestMapping("getPositionByDpName")
    @ResponseBody
    public List<String> getPositionByDpName(String dpName){
        // 根据dpName部门名称，获取该部门下的所有职位的名称
        List<Position> position = positionBiz.getPositionByDpName(dpName);
        List<String> pNames = new ArrayList<>();
        if (position == null){
            return pNames;
        }
        for (Position p : position) {
            pNames.add(p.getpName());
        }
        return pNames;
    }

    @RequestMapping("addRecruit")
    public String addRecruit(Recruit recruit, String dpName, String pName, Model model){
//        添加招聘信息，初始状态为草稿 rcState=1，
        if (recruit.getRcDeadline() == null || dpName == null || pName == null){
            model.addAttribute("str","信息不完整，添加失败");
            return "forward:recruit";
        }
        Department department = departmentBiz.getDepartmentByDpName(dpName);
        Position position = positionBiz.getPositionByPName(pName);
        recruit.setDepartment(department);
        recruit.setPosition(position);
        recruit.setRcRelease(new Date(0));
        recruit.setRcBackout(new Date(0));
        System.out.println("addRecruit: " + recruit);
        boolean flag = recruitBiz.addRecruit(recruit);
        if (flag){
            model.addAttribute("str","添加成功");
        }else {
            model.addAttribute("str","截止日期在当前日期之前，添加失败");
        }
        return "forward:recruit";
    }

    @RequestMapping("delRecruit")
    public String delRecruit(Integer rcId, Model model){
        // 删除招聘信息
        recruitBiz.delRecruit(rcId);
        return "forward:recruit";
    }

    @RequestMapping("updateRecruit")
    public String updateRecruit(Recruit recruit, Model model){
        // 只能修改截止时间，想要修改部门和职位，删除重新来
        Recruit rc = recruitBiz.getRecruitById(recruit.getRcId());
        rc.setRcDeadline(recruit.getRcDeadline());
        boolean flag = recruitBiz.updateRecruit(rc);

        if (flag){
            model.addAttribute("str","修改成功");
        }else {
            model.addAttribute("str","截止日期在当前日期之前，修改失败");
        }
        return "forward:recruit";
    }

    @RequestMapping("updateRcState")
    public String updateRcState(Recruit recruit, Model model){
        // recruit中有rcId和rcState，更新rcState，还要更新时间。先找到此招聘信息，再更新
        System.out.println("updateRcState: " + recruit);
        Recruit rc = recruitBiz.getRecruitById(recruit.getRcId());
        rc.setRcState(recruit.getRcState());
        System.out.println("updateRcState: " + rc);
        java.util.Date date = new java.util.Date();
        if (rc.getRcState() == 1){
            //
        }else if (rc.getRcState() == 2){
            // 更新发布时间
            rc.setRcRelease(new Date(date.getTime()));
        }else if (rc.getRcState() == 3){
            // 更新撤销时间
            rc.setRcBackout(new Date(date.getTime()));
        }
        recruitBiz.updateRecruit(rc);
        return "forward:recruit";
    }

    @RequestMapping("recruit")
    public String recruit(Integer pageNo, Recruit rc,  Model model){
        // 两个个入口，1、rc不为null，但是所有属性为null 2、rc里面只有rcState 查找rcState状态的职位
        // 显示部门信息，用来二级联动，选择职位
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        System.out.println("recruit: " + rc);
        Page<Recruit> recruitPage = new Page<>();
        if (rc.getRcState() == null || rc.getRcState() == 0){
            //页面跳转时选择，有确定的状态
            recruitPage = recruitBiz.getAllRecruitsByLimit(pageNo);
        }else {
            recruitPage = recruitBiz.getRecruitsByRcStateAndLimit(rc.getRcState(),pageNo);
        }
        System.out.println("recruit: " + recruitPage.getList());
        model.addAttribute("recruitPage",recruitPage);
        return "recruit";
    }
}
