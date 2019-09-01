package com.wuju.biz.bizImpl;

import com.wuju.biz.InterviewBiz;
import com.wuju.dao.InterviewDao;
import com.wuju.model.Interview;
import com.wuju.model.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class InterviewBizImpl implements InterviewBiz {
    @Resource
    private InterviewDao interviewDao;

    @Override
    public boolean addInterview(Interview it) {
        return interviewDao.addInterview(it);
    }

    @Override
    public boolean updateInterview(Interview it) {
        return interviewDao.updateInterview(it);
    }

    @Override
    public Interview getInterviewById(int itId) {
        return interviewDao.getInterviewById(itId);
    }

    @Override
    public List<Interview> getInterviewByuId(int uId) {
        return interviewDao.getInterviewByuId(uId);
    }

    @Override
    public Interview getInterviewByrcIdAnduId(int rcId, int uId) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("rcId", rcId);
        map.put("uId", uId);
        return interviewDao.getInterviewByrcIdAnduId(map);
    }

    @Override
    public Page<Interview> getAllInterviewsByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = interviewDao.getAllInterviewsCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Interview> interviews = interviewDao.getAllInterviewsByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(interviews);
        return page;
    }

    @Override
    public Page<Interview> getInterviewByStateAndLimit(int itState, int pageNo) {
        Page page=new Page<>();
        int totalRows = interviewDao.getInterviewCountByState(itState);
        HashMap<String,Object> map = new HashMap<>();
        map.put("itState",itState);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Interview> interviews = interviewDao.getInterviewByStateAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(interviews);
        return page;
    }

    @Override
    public Page<Interview> getInterviewByuIdAndLimit(int uId, int pageNo) {
        Page page=new Page<>();
        int totalRows = interviewDao.getInterviewCountByuId(uId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("uId",uId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Interview> interviews = interviewDao.getInterviewByuIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(interviews);
        return page;
    }
}
