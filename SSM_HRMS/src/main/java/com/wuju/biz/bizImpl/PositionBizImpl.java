package com.wuju.biz.bizImpl;

import com.wuju.biz.PositionBiz;
import com.wuju.dao.PositionDao;
import com.wuju.model.Department;
import com.wuju.model.Employee;
import com.wuju.model.Position;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class PositionBizImpl implements PositionBiz {
    @Resource
    private PositionDao positionDao;

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
}
