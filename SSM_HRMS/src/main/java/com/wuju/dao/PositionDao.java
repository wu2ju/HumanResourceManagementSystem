package com.wuju.dao;

import com.wuju.model.Position;

import java.util.HashMap;
import java.util.List;

public interface PositionDao {
    boolean addPosition(Position p);
    boolean updatePosition(Position p);
    boolean delPosition(int pId);
//    Position getPosition(Position p);
    Position getPosition(HashMap<String,Integer> map);
    // map中有pId, 和员工的状态eState
    //用于查询该职位下在职的员工
    Position getPositionById(int pId);
    Position getPositionByPName(String pName);
    List<Position> getAllPositions();
}
