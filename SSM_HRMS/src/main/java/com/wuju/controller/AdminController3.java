package com.wuju.controller;

import com.wuju.biz.*;
import com.wuju.model.*;
import com.wuju.util.ControllerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
* 管理员的培训功能，
* */
@Controller
public class AdminController3 {
    @Resource
    private SalaryBiz salaryBiz;
    @Resource
    private EmployeeBiz employeeBiz;

    @RequestMapping("updateSalary")
    public String updateSalary(Integer slId, Model model){
        // 结算上个月的薪资
        boolean b = salaryBiz.updateSalary(slId);
        return "forward:aSalary";
    }

    @RequestMapping("addSalary")
    public String addSalary(HttpSession session, Model model){
        // 结算上个月的薪资
        boolean b = salaryBiz.addSalary();
        if (!b){
            model.addAttribute("str","已经结算");
        }
        return "forward:aSalary";
    }

    @RequestMapping("aSalary")
    public String aSalary(Integer pageNo, Integer eId, HttpSession session, Model model){
        // 查看员工所有的打卡记录。
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Employee e = (Employee) session.getAttribute("e");
        Page<Salary> salaryPage = new Page<>();
        if (e.geteType() == 1){
            salaryPage = salaryBiz.getSalaryByeIdAndLimit(e.geteId(),pageNo);
        }else {
            if (eId != null){
                salaryPage = salaryBiz.getSalaryByeIdAndLimit(eId,pageNo);
            }else {
                salaryPage = salaryBiz.getAllSalarysByLimit(pageNo);
            }
        }
        List<Employee> employees = employeeBiz.getAllEmployees();
        model.addAttribute("employees",employees);
        model.addAttribute("salaryPage",salaryPage);
        return "aSalary";
    }

}
