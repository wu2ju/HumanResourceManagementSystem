package com.wuju.dao;

import com.wuju.model.Position;
import com.wuju.model.Recruit;

import java.util.HashMap;
import java.util.List;

public interface RecruitDao {
    boolean addRecruit(Recruit rc);
    boolean updateRecruit(Recruit rc);
    boolean delRecruit(int rcId);
    Recruit getRecruitById(int rcId);
    List<Recruit> getAllRecruits();
    List<Recruit> getRecruitByPId(int pId);
    List<Recruit> getAllRecruitsByLimit(HashMap map);
    int getAllRecruitsCount();
    List<Recruit> getRecruitByDpIdAndStateAndLimit(HashMap map);
    int getRecruitCountByDpIdAndState(HashMap map);
    List<Recruit> getRecruitByRcStateAndLimit(HashMap map);
    int getRecruitCountByRcState(int rcState);
}
