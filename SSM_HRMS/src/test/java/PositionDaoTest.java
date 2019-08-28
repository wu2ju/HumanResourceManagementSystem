import com.wuju.dao.EmployeeDao;
import com.wuju.dao.PositionDao;
import com.wuju.model.Employee;
import com.wuju.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class PositionDaoTest {
    @Resource
    private PositionDao positionDao;
    @Test
    public void testGetPosition(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("pId",4);
        map.put("eState",0); // 员工状态为0，表示员工在职
        Position p = positionDao.getPosition(map);
        System.out.println(p);
        System.out.println(p.getEmployees());
        System.out.println(p.getDepartment());
    }

    @Test
    public void testGetPositionById(){
        Position p = positionDao.getPositionById(4);
        System.out.println(p);
        System.out.println(p.getEmployees());
        System.out.println(p.getDepartment());
    }

}