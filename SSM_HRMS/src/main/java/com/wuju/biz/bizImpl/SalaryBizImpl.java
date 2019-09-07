package com.wuju.biz.bizImpl;

import com.wuju.biz.SalaryBiz;
import com.wuju.dao.*;
import com.wuju.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class SalaryBizImpl implements SalaryBiz {
    @Resource
    private SalaryDao salaryDao;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private QuitDao quitDao;
    @Resource
    private CheckInDao checkInDao;
    @Resource
    private RewardPunishDao rewardPunishDao;

    @Override
    public boolean addSalary() {
        // 找出每个在职员工（只算离职员工的上个月离职的工资）的奖惩记录，
        // 奖金是工作满22天，奖励1000
        // 社保是工资的5%
        List<Employee> employees = employeeDao.getAllEmployees();
        int count = salaryDao.getSalaryByMonth();
        if (count >0){
            return false;
        }
        for (Employee e : employees) {
            double slBase = 0;
            double slBase1 = 0;
            double slBase2 = 0;
            double slBonus = 0;
            double slRp = 0;
            double slSecurity = 0;
            double slTotal = 0;
            double slReal = 0;
            if (e.geteState() ==2){
                // 离职
                Quit quit = quitDao.getQuitMonth(e.geteId()); // 找出上个月或这个月离职的
                if (quit == null){
                     continue;
                }
            }

                // 开始结算工资
                Employee employee = employeeDao.getEmployeeById(e.geteId());
                System.out.println(employee);
                slBase = employee.getPosition().getpSalary();
                if (e.geteState() == 0){
                    slBase = slBase * 0.6;
                }
                slSecurity = slBase * 0.02;

                List<CheckIn> checkIns = checkInDao.getCheckInMonthByeId(e.geteId());
                if (checkIns != null && checkIns.size()>0){
                    for (CheckIn checkIn : checkIns) {
                        if (checkIn.getCiClosetime().getTime() < 1){
                            // 没有生成奖惩记录
                            RewardPunish rp = new RewardPunish();
                            rp.setRpRecord(1);
                            rp.setEmployee(employee);
                            rp.setRpMoney(-1*slBase/22);
                            rp.setRpTime(checkIn.getCiAttendtime());
                            rp.setRpReason("上班时间0小时");
                            rewardPunishDao.addRewardPunish(rp);
                        }
                    }
                    slBase1 = slBase/22*checkIns.size();
                    slBase2 = slBase1;
                    if (checkIns.size() >= 22){
                        slBonus = 1000;
                    }
                }
                // 获取该员工上个月的奖惩记录
                List<RewardPunish> rewardPunishes = rewardPunishDao.getRewardPunishMonthByeId(employee.geteId());
                if (rewardPunishes!=null && rewardPunishes.size()>0){
                    for (RewardPunish rp : rewardPunishes) {
                        slRp += rp.getRpMoney();
                    }
                    slBase1 += slRp; // 奖惩之后
                }

                slTotal = slBase1 + slBonus - slSecurity;
                slReal = slTotal;
                if (slTotal < 0){
                    slReal = 0;
                }
                Date date = new Date();
                java.sql.Date slDate = new java.sql.Date(date.getTime());
                Salary salary = new Salary(slBase2,slBonus,slRp,slSecurity,slDate,1,slTotal,slReal,employee);
                salaryDao.addSalary(salary);
        }

        return true;
    }

    @Override
    public boolean updateSalary(int slId) {
        return salaryDao.updateSalary(slId);
    }

    @Override
    public Page<Salary> getSalaryByeIdAndLimit(int eId, int pageNo) {
        Page page=new Page<>();
        int totalRows = salaryDao.getSalaryCountByeId(eId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("eId",eId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Salary> salaries = salaryDao.getSalaryByeIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(salaries);
        return page;
    }

    @Override
    public Page<Salary> getAllSalarysByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = salaryDao.getAllSalaryCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Salary> salaries = salaryDao.getAllSalarysByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(salaries);
        return page;
    }
}
