package com.wuju.controller;

import com.wuju.biz.*;
import com.wuju.model.*;
import com.wuju.util.ControllerUtil;
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


    @RequestMapping("addRewardPunish")
    public String addRewardPunish(RewardPunish rp, String rpTime1, Double rpMoney1, Integer eId, HttpSession session, Model model){
        if(rp.getRpReason()==null || rp.getRpReason().equals("") || rpTime1==null || rpTime1.equals("") ||
                rpMoney1 == null || eId == null){
            model.addAttribute("str", "信息不正确");
            return "forward:eRewardPunish";
        }
        Timestamp rpTime = ControllerUtil.strToTimestamp(rpTime1);
        rp.setRpTime(rpTime);
        rp.setRpMoney(rpMoney1);
        rp.setEmployee(employeeBiz.getEmployeeById(eId));
        rp.setRpRecord(2);
        rewardPunishBiz.addRewardPunish(rp);
        return "forward:eRewardPunish";
    }

    @RequestMapping("updateRewardPunish")
    public String updateRewardPunish(RewardPunish rp, String rpTime1, Double rpMoney1, HttpSession session, Model model){
        if(rp.getRpReason()==null || rp.getRpReason().equals("") || rpTime1==null || rpTime1.equals("") ||
        rpMoney1 == null){
            model.addAttribute("str", "信息不正确");
            return "forward:eRewardPunish";
        }
        RewardPunish rp1 = rewardPunishBiz.getRewardPunishById(rp.getRpId());
        Timestamp rpTime = ControllerUtil.strToTimestamp(rpTime1);
        rp.setRpTime(rpTime);
        rp.setRpMoney(rpMoney1);
        rp.setEmployee(rp1.getEmployee());
        rp.setRpRecord(2);
        rewardPunishBiz.updateRewardPunish(rp);
        return "forward:eRewardPunish";
    }

    @RequestMapping("eRewardPunish")
    public String eRewardPunish(Integer pageNo, Integer month, Integer eId, HttpSession session, Model model){
        // 查看员工所有的打卡记录。
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Employee e = (Employee) session.getAttribute("e");
        Page<RewardPunish> rewardPunishPage = new Page<>();
        if (e.geteType() == 1){
            rewardPunishPage = rewardPunishBiz.getRewardPunishByeIdAndLimit(e.geteId(),pageNo);
        }else {
            if (month == null){
                month = 1;
            }
            if (eId != null){
                rewardPunishPage = rewardPunishBiz.getRewardPunishMonthByeIdAndLimit(month,eId,pageNo);
            }else {
                rewardPunishPage = rewardPunishBiz.getRewardPunishMonthByLimit(month, pageNo);
            }
        }
        List<Employee> employees = employeeBiz.getAllEmployees();
        model.addAttribute("employees",employees);
        model.addAttribute("rewardPunishPage",rewardPunishPage);
        return "eRewardPunish";
    }

    @RequestMapping("eCheckIn")
    public String eCheckIn(String method, HttpSession session, Model model){
        // 考勤打卡，员工上班时间，下班时间，显示今天的打卡记录。
        Date date = new Date();
        Employee e = (Employee) session.getAttribute("e");
        CheckIn checkInToday = checkInBiz.getCheckInToday();
        if (method != null){
            if (method.equals("attend")){
                // 上班的话，添加打卡记录，今天上班已经打卡了，那就不能再打卡了
                if (checkInToday == null){
                    CheckIn checkIn = new CheckIn(new Timestamp(date.getTime()),new Timestamp(0),e);
                    checkInBiz.addCheckIn(checkIn);
                }
            }else if(method.equals("close")){
                // 打下班卡，如果今天没打上班卡，则不能打下班卡
                if (checkInToday == null){
                    model.addAttribute("str","没有打上班卡");
                }else {
                    checkInToday.setCiClosetime(new Timestamp(date.getTime()));
                    checkInBiz.updateCheckIn(checkInToday);
                    double hour = (checkInToday.getCiClosetime().getTime()-checkInToday.getCiAttendtime().getTime())/1000/3600;
                    RewardPunish rp = rewardPunishBiz.getRewardPunishByeIdAndRpTimeAndRpRecord(e.geteId(), 1);
                    int hourCal = 0;
                    if (hour < 9){
                        //生成一条惩罚记录
                        hourCal = (int) Math.floor(hour);
                    }else if (hour >= 9){
                        //生成一条奖励记录
                        hourCal = (int) Math.ceil(hour);
                    }
                    double money = salaryEveryHour(e);
                    if (rp == null){
                        RewardPunish rp1 = new RewardPunish(new Timestamp(date.getTime()),"上班时间"+ hourCal +"小时",e,1,money*(hourCal-9));
                        rewardPunishBiz.addRewardPunish(rp1);
                    }else {
                        rp.setRpTime(new Timestamp(date.getTime()));
                        rp.setRpReason("上班时间"+ hourCal +"小时");
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
            // 试用期，工资只有正式工资的六折
            money = salary * 0.6 /22/9;  //一个月工作22天，每天9小时
        }else if (employee.geteState() == 1){
            // 正式工作，工资就是该职位的工资
            money = salary/22/9;
        }
        return money;
    }

    @RequestMapping("eCheckInLog")
    public String eCheckInLog(Integer pageNo, Integer eId, HttpSession session, Model model){
        // 查看员工所有的打卡记录。
        Employee e = (Employee) session.getAttribute("e");
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<CheckIn> checkInPage = new Page<>();
        // 员工
        if (e.geteType() == 1){
            checkInPage = checkInBiz.getCheckInByeIdAndLimit(e.geteId(),pageNo);
        }else {
            // 管理员要么查看所有的考勤记录，或者某个员工的考勤记录
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
