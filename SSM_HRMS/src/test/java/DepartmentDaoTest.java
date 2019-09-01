import com.wuju.biz.DepartmentBiz;
import com.wuju.biz.bizImpl.DepartmentBizImpl;
import com.wuju.dao.DepartmentDao;
import com.wuju.dao.PositionDao;
import com.wuju.model.Department;
import com.wuju.model.Position;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class DepartmentDaoTest {
    @Resource
    private DepartmentDao departmentDao;

    private DepartmentBiz departmentBiz = new DepartmentBizImpl();

    @Test
    public void testGetDepartmentByLimit(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("start",1);
        map.put("end",3);
        List<Department> departments = departmentDao.getAllDepartmentsByLimit(map);
        System.out.println(departments);
    }

    @Test
    public void testGetDepartmentCount(){
        int count = departmentDao.getAllDepartmentsCount();
        System.out.println(count);
    }

    @Test
    public void testGetDepartment(){
        Department d = new Department(3);
//        d.setDpName("���۲�");
        Department department = departmentDao.getDepartment(d);
        System.out.println(department);
        System.out.println(department.getPositions());
    }



    @Test
    public void testGetDepartment2(){
        Department d = new Department(3);
        Department department = departmentBiz.getDepartment(d);
        System.out.println(department);
        System.out.println(department.getPositions());
    }

    @Test
    public void testGetDepartmentById(){
        Department department = departmentDao.getDepartmentById(3);
        System.out.println(department);
        System.out.println(department.getPositions());
    }
}