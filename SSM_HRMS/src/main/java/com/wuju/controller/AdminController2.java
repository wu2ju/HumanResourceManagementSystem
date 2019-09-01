package com.wuju.controller;

import com.wuju.biz.DepartmentBiz;
import com.wuju.biz.PositionBiz;
import com.wuju.biz.RecruitBiz;
import com.wuju.model.Department;
import com.wuju.model.Page;
import com.wuju.model.Position;
import com.wuju.model.Recruit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController2 {
    @Resource
    private RecruitBiz recruitBiz;
    @Resource
    private DepartmentBiz departmentBiz;
    @Resource
    private PositionBiz positionBiz;

    @RequestMapping("getPositionByDpName")
    @ResponseBody
    public List<String> getPositionByDpName(String dpName){
        // 根据dpName部门名称，获取该部门下的所有职位的名称
        List<Position> position = positionBiz.getPositionByDpName(dpName);
        List<String> pNames = new ArrayList<>();
        for (Position p : position) {
            pNames.add(p.getpName());
        }
        return pNames;
    }

    @RequestMapping("addRecruit")
    public String addRecruit(Recruit recruit, String dpName, String pName, Model model){
//        添加招聘信息，初始状态为草稿 rcState=1，
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
