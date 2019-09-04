package com.wuju.dao;

import com.wuju.model.Interview;

import java.util.HashMap;
import java.util.List;

public interface InterviewDao {
    boolean addInterview(Interview it);
    boolean updateInterview(Interview it);
    boolean delInterview(int itId);
    Interview getInterviewById(int itId);
    List<Interview> getInterviewByITtime(HashMap map);
    Interview getInterviewByrcIdAnduId(HashMap map);
    //一个招聘岗位，一个用户只能投递一次简历
    List<Interview> getInterviewByuId(int uId);
    List<Interview> getAllInterviewsByLimit(HashMap map);
    int getAllInterviewsCount();
    List<Interview> getInterviewByStateAndLimit(HashMap map);
    int getInterviewCountByState(int itState);
    List<Interview> getInterviewByuIdAndLimit(HashMap map);
    int getInterviewCountByuId(int uId);
}
