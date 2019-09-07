package com.wuju.dao;

import com.wuju.model.CheckIn;
import com.wuju.model.Quit;

import java.util.HashMap;
import java.util.List;

public interface QuitDao {
    boolean addQuit(Quit quit);
    // 找出这个月或者上个月离职的员工
    Quit getQuitMonth(int eId);
}
