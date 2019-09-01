package com.wuju.biz.bizImpl;

import com.wuju.biz.RecruitBiz;
import com.wuju.dao.RecruitDao;
import com.wuju.model.Page;
import com.wuju.model.Recruit;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RecruitBizImpl implements RecruitBiz {
    @Resource
    private RecruitDao recruitDao;

    @Override
    public boolean addRecruit(Recruit rc) {
        // 添加招聘信息
        // 如果截止时间，在当前时间之前，那么添加失败，否则添加成功
        if (rc.getRcDeadline().before(new Date())){
            return false;
        }
        recruitDao.addRecruit(rc);
        return true;
    }

    @Override
    public boolean updateRecruit(Recruit rc) {
        // 招聘信息为草稿时，可以修改截止时间、招聘的职位和部门
        // 发布招聘信息，修改发布时间、状态
        // 撤销招聘信息，到了截止时间自动撤销，修改撤销时间和状态，
        // 处于撤销状态的招聘信息，可以选择进入草稿状态，修改之后重新发布

        // 如果截止时间，在当前时间之前，那么修改失败
        if (rc.getRcDeadline().before(new Date())){
            return false;
        }
        recruitDao.updateRecruit(rc);
        return true;
    }

    @Override
    public boolean delRecruit(int rcId) {
        return recruitDao.delRecruit(rcId);
    }

    @Override
    public Recruit getRecruitById(int rcId) {
        return recruitDao.getRecruitById(rcId);
    }

    @Override
    public List<Recruit> getAllRecruits() {
        return recruitDao.getAllRecruits();
    }

    @Override
    public Page<Recruit> getAllRecruitsByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = recruitDao.getAllRecruitsCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Recruit> recruits = recruitDao.getAllRecruitsByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(recruits);
        return page;
    }

    @Override
    public Page<Recruit> getRecruitsByDpIdAndStateAndLimit(int dpId, int pageNo) {
        Page page=new Page<>();
        HashMap<String,Object> map = new HashMap<>();
        map.put("dpId",dpId);
        map.put("rcState",2); // 只能查到已经发布的招聘信息
        int totalRows = recruitDao.getRecruitCountByDpIdAndState(map);
        /*HashMap<String,Object> map = new HashMap<>();
        map.put("dpId",dpId);
        map.put("rcState",2); // 只能查到已经发布的招聘信息*/
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Recruit> recruits = recruitDao.getRecruitByDpIdAndStateAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(recruits);
        return page;
    }

    @Override
    public Page<Recruit> getRecruitsByRcStateAndLimit(int rcState, int pageNo) {
        Page page=new Page<>();
        int totalRows = recruitDao.getRecruitCountByRcState(rcState);
        HashMap<String,Object> map = new HashMap<>();
        map.put("rcState",rcState);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Recruit> recruits = recruitDao.getRecruitByRcStateAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(recruits);
        return page;
    }
}
