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
//        职位创建选择是哪个部门下的职位创建；职位新增重名判断；
        Position position = positionDao.getPositionByPName(p.getpName());
        if (position != null){
            return false;
        }
        positionDao.addPosition(p);
        return true;
    }

    @Override
    public boolean updatePosition(Position p) {
        //        更改职位；进行重名判断；
        /*Position position = positionDao.getPositionByPName(p.getpName());
        if (position != null){
            return false;
        }*/
        positionDao.updatePosition(p);
        return true;
    }

    @Override
    public boolean delPosition(int pId) {
//        若该职位下存在在职员工，不能进行删除；
        HashMap<String,Integer> map = new HashMap<>();
        map.put("pId",pId);
        map.put("eState",0); // 员工状态为0，表示员工在职
        Position p = positionDao.getPosition(map);
        // 如果职位中没有员工，或者没有在职员工，那么p为null
        if (p != null){
            //此Position的对象p中包含Department和eState=0的Employee
            List<Employee> employees = p.getEmployees();
            if (employees != null && employees.size() > 0){
                return false; // 表明这个职位有员工，此职位不能删除
            }
        }
        positionDao.delPosition(pId);
        return true;
    }

    @Override
    public Position getPosition(HashMap<String, Integer> map) {
//        可以通过查看某职位，可以查看该职位下的员工；
//        map中包含职位pId和员工状态eState
        return positionDao.getPosition(map);
    }

    @Override
    public List<Position> getAllPositions() {
        return positionDao.getAllPositions();
    }
}
