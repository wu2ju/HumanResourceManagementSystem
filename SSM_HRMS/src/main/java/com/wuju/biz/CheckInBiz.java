package com.wuju.biz;

import com.wuju.model.CheckIn;
import com.wuju.model.Page;
import com.wuju.model.Train;

import java.util.List;

public interface CheckInBiz {
    boolean addCheckIn(CheckIn ci);
    boolean updateCheckIn(CheckIn ci);
    boolean delCheckIn(int ciId);
    CheckIn getCheckInById(int ciId);
    Page<CheckIn> getCheckInByeIdAndLimit(int eId, int pageNo);
    Page<CheckIn> getAllCheckInsByLimit(int pageNo);
    CheckIn getCheckInToday();
}
