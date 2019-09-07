package com.wuju.dao;

import com.wuju.model.CheckIn;
import com.wuju.model.Train;

import java.util.HashMap;
import java.util.List;

public interface CheckInDao {
    boolean addCheckIn(CheckIn ci);
    boolean updateCheckIn(CheckIn ci);
    boolean delCheckIn(int ciId);
    CheckIn getCheckInById(int ciId);
    List<CheckIn> getCheckInByeIdAndLimit(HashMap map);
    int getCheckInCountByeId(int eId);
    List<CheckIn> getAllCheckInsByLimit(HashMap map);
    int getAllCheckInCount();
    List<CheckIn> getCheckInMonthByeId(int eId);
    // 获取上个月的考勤记录
    List<CheckIn> getAllCheckIns();
    CheckIn getCheckInToday();
}
