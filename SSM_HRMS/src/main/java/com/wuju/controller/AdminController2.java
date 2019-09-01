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
        // ����dpName�������ƣ���ȡ�ò����µ�����ְλ������
        List<Position> position = positionBiz.getPositionByDpName(dpName);
        List<String> pNames = new ArrayList<>();
        for (Position p : position) {
            pNames.add(p.getpName());
        }
        return pNames;
    }

    @RequestMapping("addRecruit")
    public String addRecruit(Recruit recruit, String dpName, String pName, Model model){
//        �����Ƹ��Ϣ����ʼ״̬Ϊ�ݸ� rcState=1��
        Department department = departmentBiz.getDepartmentByDpName(dpName);
        Position position = positionBiz.getPositionByPName(pName);
        recruit.setDepartment(department);
        recruit.setPosition(position);
        recruit.setRcRelease(new Date(0));
        recruit.setRcBackout(new Date(0));
        System.out.println("addRecruit: " + recruit);
        boolean flag = recruitBiz.addRecruit(recruit);
        if (flag){
            model.addAttribute("str","��ӳɹ�");
        }else {
            model.addAttribute("str","��ֹ�����ڵ�ǰ����֮ǰ�����ʧ��");
        }
        return "forward:recruit";
    }

    @RequestMapping("delRecruit")
    public String delRecruit(Integer rcId, Model model){
        // ɾ����Ƹ��Ϣ
        recruitBiz.delRecruit(rcId);
        return "forward:recruit";
    }

    @RequestMapping("updateRecruit")
    public String updateRecruit(Recruit recruit, Model model){
        // ֻ���޸Ľ�ֹʱ�䣬��Ҫ�޸Ĳ��ź�ְλ��ɾ��������
        Recruit rc = recruitBiz.getRecruitById(recruit.getRcId());
        rc.setRcDeadline(recruit.getRcDeadline());
        boolean flag = recruitBiz.updateRecruit(rc);

        if (flag){
            model.addAttribute("str","�޸ĳɹ�");
        }else {
            model.addAttribute("str","��ֹ�����ڵ�ǰ����֮ǰ���޸�ʧ��");
        }
        return "forward:recruit";
    }

    @RequestMapping("updateRcState")
    public String updateRcState(Recruit recruit, Model model){
        // recruit����rcId��rcState������rcState����Ҫ����ʱ�䡣���ҵ�����Ƹ��Ϣ���ٸ���
        System.out.println("updateRcState: " + recruit);
        Recruit rc = recruitBiz.getRecruitById(recruit.getRcId());
        rc.setRcState(recruit.getRcState());
        System.out.println("updateRcState: " + rc);
        java.util.Date date = new java.util.Date();
        if (rc.getRcState() == 1){
            //
        }else if (rc.getRcState() == 2){
            // ���·���ʱ��
            rc.setRcRelease(new Date(date.getTime()));
        }else if (rc.getRcState() == 3){
            // ���³���ʱ��
            rc.setRcBackout(new Date(date.getTime()));
        }
        recruitBiz.updateRecruit(rc);
        return "forward:recruit";
    }

    @RequestMapping("recruit")
    public String recruit(Integer pageNo, Recruit rc,  Model model){
        // ��������ڣ�1��rc��Ϊnull��������������Ϊnull 2��rc����ֻ��rcState ����rcState״̬��ְλ
        // ��ʾ������Ϣ����������������ѡ��ְλ
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        System.out.println("recruit: " + rc);
        Page<Recruit> recruitPage = new Page<>();
        if (rc.getRcState() == null || rc.getRcState() == 0){
            //ҳ����תʱѡ����ȷ����״̬
            recruitPage = recruitBiz.getAllRecruitsByLimit(pageNo);
        }else {
            recruitPage = recruitBiz.getRecruitsByRcStateAndLimit(rc.getRcState(),pageNo);
        }
        System.out.println("recruit: " + recruitPage.getList());
        model.addAttribute("recruitPage",recruitPage);
        return "recruit";
    }
}
