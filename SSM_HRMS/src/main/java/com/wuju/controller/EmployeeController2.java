package com.wuju.controller;

import com.wuju.biz.*;
import com.wuju.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController2 {
    @Resource
    private EmployeeBiz employeeBiz;
    @Resource
    private CheckInBiz checkInBiz;
    @Resource
    private RewardPunishBiz rewardPunishBiz;


    @RequestMapping("eRewardPunish")
    public String eRewardPunish(Integer pageNo, HttpSession session, Model model){
        // �鿴Ա�����еĴ򿨼�¼��
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Employee e = (Employee) session.getAttribute("e");
        Page<RewardPunish> rewardPunishPage = rewardPunishBiz.getRewardPunishByeIdAndLimit(e.geteId(),pageNo);
        model.addAttribute("rewardPunishPage",rewardPunishPage);
        return "eRewardPunish";
    }

    @RequestMapping("eCheckIn")
    public String eCheckIn(String method, HttpSession session, Model model){
        // ���ڴ򿨣�Ա���ϰ�ʱ�䣬�°�ʱ�䣬��ʾ����Ĵ򿨼�¼��
        Date date = new Date();
        Employee e = (Employee) session.getAttribute("e");
        CheckIn checkInToday = checkInBiz.getCheckInToday();
        if (method != null){
            if (method.equals("attend")){
                // �ϰ�Ļ�����Ӵ򿨼�¼�������ϰ��Ѿ����ˣ��ǾͲ����ٴ���
                if (checkInToday == null){
                    CheckIn checkIn = new CheckIn(new Timestamp(date.getTime()),new Timestamp(0),e);
                    checkInBiz.addCheckIn(checkIn);
                }
            }else if(method.equals("close")){
                // ���°࿨���������û���ϰ࿨�����ܴ��°࿨
                if (checkInToday == null){
                    model.addAttribute("str","û�д��ϰ࿨");
                }else {
                    checkInToday.setCiClosetime(new Timestamp(date.getTime()));
                    checkInBiz.updateCheckIn(checkInToday);
                    double hour = (checkInToday.getCiClosetime().getTime()-checkInToday.getCiAttendtime().getTime())/1000/3600;
                    RewardPunish rp = rewardPunishBiz.getRewardPunishByeIdAndRpTimeAndRpRecord(e.geteId(), 1);
                    int hourCal = 0;
                    if (hour < 9){
                        //����һ���ͷ���¼
                        hourCal = (int) Math.floor(hour);
                    }else if (hour > 10){
                        //����һ��������¼
                        hourCal = (int) Math.ceil(hour);
                    }
                    double money = salaryEveryHour(e);
                    if (rp == null){
                        RewardPunish rp1 = new RewardPunish(new Timestamp(date.getTime()),"�ϰ�ʱ��"+ hourCal +"Сʱ",e,1,money*(hourCal-9));
                        rewardPunishBiz.addRewardPunish(rp1);
                    }else {
                        rp.setRpTime(new Timestamp(date.getTime()));
                        rp.setRpReason("�ϰ�ʱ��"+ hourCal +"Сʱ");
                        rp.setRpMoney(money*(hourCal-9));
                        rewardPunishBiz.updateRewardPunish(rp);
                    }
                }
            }
        }
        CheckIn checkIn = checkInBiz.getCheckInToday();
        model.addAttribute("checkIn",checkIn);
        return "eCheckIn";
    }

    private double salaryEveryHour(Employee e) {
        Employee employee = employeeBiz.getEmployeeById(e.geteId());
        double salary = employee.getPosition().getpSalary();
        double money = 0;
        if (employee.geteState() == 0){
            // �����ڣ�����ֻ����ʽ���ʵ�����
            money = salary * 0.6 /22/9;  //һ���¹���22�죬ÿ��9Сʱ
        }else if (employee.geteState() == 1){
            // ��ʽ���������ʾ��Ǹ�ְλ�Ĺ���
            money = salary/22/9;
        }
        return money;
    }

    @RequestMapping("eCheckInLog")
    public String eCheckInLog(Integer pageNo, Integer eId, HttpSession session, Model model){
        // �鿴Ա�����еĴ򿨼�¼��
        Employee e = (Employee) session.getAttribute("e");
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<CheckIn> checkInPage = new Page<>();
        // Ա��
        if (e.geteType() == 1){
            checkInPage = checkInBiz.getCheckInByeIdAndLimit(e.geteId(),pageNo);
        }else {
            // ����ԱҪô�鿴���еĿ��ڼ�¼������ĳ��Ա���Ŀ��ڼ�¼
            if (eId == null){
                checkInPage = checkInBiz.getAllCheckInsByLimit(pageNo);
            }else {
                checkInPage = checkInBiz.getCheckInByeIdAndLimit(eId,pageNo);
            }
            List<Employee> employees = employeeBiz.getAllEmployees();
            model.addAttribute("employees",employees);
        }
        model.addAttribute("checkInPage",checkInPage);
        return "eCheckInLog";
    }
}
