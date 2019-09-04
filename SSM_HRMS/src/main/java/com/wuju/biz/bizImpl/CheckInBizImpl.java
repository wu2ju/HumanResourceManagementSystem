package com.wuju.biz.bizImpl;

import com.wuju.biz.CheckInBiz;
import com.wuju.dao.CheckInDao;
import com.wuju.model.CheckIn;
import com.wuju.model.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CheckInBizImpl implements CheckInBiz {
    @Resource
    private CheckInDao checkInDao;
    @Override
    public boolean addCheckIn(CheckIn ci) {
        return checkInDao.addCheckIn(ci);
    }

    @Override
    public boolean updateCheckIn(CheckIn ci) {
        return checkInDao.updateCheckIn(ci);
    }

    @Override
    public boolean delCheckIn(int ciId) {
        return checkInDao.delCheckIn(ciId);
    }

    @Override
    public CheckIn getCheckInById(int ciId) {
        return checkInDao.getCheckInById(ciId);
    }

    @Override
    public Page<CheckIn> getCheckInByeIdAndLimit(int eId, int pageNo ) {
        Page page=new Page<>();
        int totalRows = checkInDao.getCheckInCountByeId(eId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("eId",eId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<CheckIn> checkIns = checkInDao.getCheckInByeIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(checkIns);
        return page;
    }

    @Override
    public Page<CheckIn> getAllCheckInsByLimit(int pageNo){
        Page page=new Page<>();
        int totalRows = checkInDao.getAllCheckInCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<CheckIn> checkIns = checkInDao.getAllCheckInsByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(checkIns);
        return page;
    }

    @Override
    public CheckIn getCheckInToday() {
        /*Calendar calendar = Calendar.getInstance();
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);
        calendar.add(Calendar.DATE,1);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);
        HashMap<String,String> map = new HashMap<>();
        map.put("day1",year1+""+month1+day1);
        map.put("day2",year2+""+month2+day2);*/
        return checkInDao.getCheckInToday();
    }
}
