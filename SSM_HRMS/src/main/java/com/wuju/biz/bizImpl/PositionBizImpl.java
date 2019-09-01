package com.wuju.biz.bizImpl;

import com.wuju.biz.PositionBiz;
import com.wuju.dao.DepartmentDao;
import com.wuju.dao.PositionDao;
import com.wuju.dao.RecruitDao;
import com.wuju.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionBizImpl implements PositionBiz {
    @Resource
    private PositionDao positionDao;
    @Resource
    private RecruitDao recruitDao;
    @Resource
    private DepartmentDao departmentDao;

    @Override
    public boolean addPosition(Position p) {
//        ְλ����ѡ�����ĸ������µ�ְλ������ְλ���������жϣ�
        Position position = positionDao.getPositionByPName(p.getpName());
        if (position != null){
            return false;
        }
        positionDao.addPosition(p);
        return true;
    }

    @Override
    public boolean updatePosition(Position p) {
        //        ����ְλ�����������жϣ�
        /*Position position = positionDao.getPositionByPName(p.getpName());
        if (position != null){
            return false;
        }*/
        positionDao.updatePosition(p);
        return true;
    }

    @Override
    public boolean delPosition(int pId) {
//        ����ְλ�´�����ְԱ�������ܽ���ɾ����
        HashMap<String,Integer> map = new HashMap<>();
        map.put("pId",pId);
        map.put("eState",0); // Ա��״̬Ϊ0����ʾԱ����ְ
        Position p = positionDao.getPosition(map);
        // ���ְλ��û��Ա��������û����ְԱ������ôpΪnull
        if (p != null){
            //��Position�Ķ���p�а���Department��eState=0��Employee
            List<Employee> employees = p.getEmployees();
            if (employees != null && employees.size() > 0){
                return false; // �������ְλ��Ա������ְλ����ɾ��
            }
        }
        // ɾ����ְλ����Ƹ��Ϣ
        List<Recruit> recruits = recruitDao.getRecruitByPId(pId);
        for (Recruit recruit : recruits) {
            recruitDao.delRecruit(recruit.getRcId());
        }
        positionDao.delPosition(pId);
        return true;
    }

    @Override
    public Position getPosition(HashMap<String, Integer> map) {
//        ����ͨ���鿴ĳְλ�����Բ鿴��ְλ�µ�Ա����
//        map�а���ְλpId��Ա��״̬eState
        return positionDao.getPosition(map);
    }

    @Override
    public List<Position> getAllPositions() {
        return positionDao.getAllPositions();
    }

    @Override
    public List<Position> getPositionByDpName(String dpName) {
        Department d = departmentDao.getDepartmentByDpName(dpName);
        return positionDao.getPositionByDpId(d.getDpId());
    }

    @Override
    public Position getPositionByPName(String pName) {
        return positionDao.getPositionByPName(pName);
    }

    @Override
    public Page<Position> getAllPositionsByLimit(int pageNo) {
        Page page=new Page<>();
        int totalRows = positionDao.getAllPositionsCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Position> positions = positionDao.getAllPositionsByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(positions);
        return page;
    }

    @Override
    public Page<Position> getPositionsByDpIdAndLimit(int dpId, int pageNo) {
        Page page=new Page<>();
        int totalRows = positionDao.getPositionCountByDpId(dpId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("dpId",dpId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Position> positions = positionDao.getPositionByDpIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(positions);
        return page;
    }
}
