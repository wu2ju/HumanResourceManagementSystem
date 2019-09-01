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
        // �����Ƹ��Ϣ
        // �����ֹʱ�䣬�ڵ�ǰʱ��֮ǰ����ô���ʧ�ܣ�������ӳɹ�
        if (rc.getRcDeadline().before(new Date())){
            return false;
        }
        recruitDao.addRecruit(rc);
        return true;
    }

    @Override
    public boolean updateRecruit(Recruit rc) {
        // ��Ƹ��ϢΪ�ݸ�ʱ�������޸Ľ�ֹʱ�䡢��Ƹ��ְλ�Ͳ���
        // ������Ƹ��Ϣ���޸ķ���ʱ�䡢״̬
        // ������Ƹ��Ϣ�����˽�ֹʱ���Զ��������޸ĳ���ʱ���״̬��
        // ���ڳ���״̬����Ƹ��Ϣ������ѡ�����ݸ�״̬���޸�֮�����·���

        // �����ֹʱ�䣬�ڵ�ǰʱ��֮ǰ����ô�޸�ʧ��
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
        map.put("rcState",2); // ֻ�ܲ鵽�Ѿ���������Ƹ��Ϣ
        int totalRows = recruitDao.getRecruitCountByDpIdAndState(map);
        /*HashMap<String,Object> map = new HashMap<>();
        map.put("dpId",dpId);
        map.put("rcState",2); // ֻ�ܲ鵽�Ѿ���������Ƹ��Ϣ*/
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
