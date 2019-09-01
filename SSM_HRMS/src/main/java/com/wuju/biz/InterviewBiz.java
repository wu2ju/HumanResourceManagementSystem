package com.wuju.biz;

import com.wuju.model.Interview;
import com.wuju.model.Page;

import java.util.List;

public interface InterviewBiz {
    boolean addInterview(Interview it);
    boolean updateInterview(Interview it);
    Interview getInterviewById(int itId);
    List<Interview> getInterviewByuId(int uId);
    Interview getInterviewByrcIdAnduId(int rcId, int uId);

    Page<Interview> getAllInterviewsByLimit(int pageNo);
    Page<Interview> getInterviewByStateAndLimit(int itState, int pageNo);
    Page<Interview> getInterviewByuIdAndLimit(int uId, int pageNo);
}
