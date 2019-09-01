package com.wuju.biz;

import com.wuju.model.Page;
import com.wuju.model.Recruit;

import java.util.HashMap;
import java.util.List;

public interface RecruitBiz {
    boolean addRecruit(Recruit rc);
    boolean updateRecruit(Recruit rc);
    boolean delRecruit(int rcId);
    Recruit getRecruitById(int rcId);
    List<Recruit> getAllRecruits();
    Page<Recruit> getAllRecruitsByLimit(int pageNo);
    Page<Recruit> getRecruitsByDpIdAndStateAndLimit(int dpId, int pageNo);
    Page<Recruit> getRecruitsByRcStateAndLimit(int rcState, int pageNo);
}
