package com.wuju.biz;

import com.wuju.model.Page;
import com.wuju.model.Position;

import java.util.HashMap;
import java.util.List;

public interface PositionBiz {
    boolean addPosition(Position p);
    boolean updatePosition(Position p);
    boolean delPosition(int pId);
    //    Position getPosition(Position p);
    Position getPosition(HashMap<String,Integer> map);
    // map����pId, ��Ա����״̬eState
    //���ڲ�ѯ��ְλ����ְ��Ա��
//    Position getPositionById(int pId);
    List<Position> getAllPositions();
    List<Position> getPositionByDpName(String dpName);
    Position getPositionByPName(String pName);
    Page<Position> getAllPositionsByLimit(int pageNo);
    Page<Position> getPositionsByDpIdAndLimit(int dpId, int pageNo);
}
