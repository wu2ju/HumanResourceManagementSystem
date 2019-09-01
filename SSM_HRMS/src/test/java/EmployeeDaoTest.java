import com.wuju.dao.EmployeeDao;
import com.wuju.dao.ResumeDao;
import com.wuju.model.Employee;
import com.wuju.model.Position;
import com.wuju.model.Resume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class EmployeeDaoTest {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private ResumeDao resumeDao;
    @Test
    public void testGetEmployee(){
        Employee e = employeeDao.getEmployeeByAccountAndPassword(new Employee("aaa","123"));
        System.out.println(e);
    }

    @Test
    public void testAddEmployee(){
        Resume r = resumeDao.getResumeByuId(1).get(0);
        Date date = new Date();
        Employee e = new Employee("111","123",1,r.getrName(),r.getrSex(),r.getrBirthday(),
                "111",111+"@qq.com",0,new Position(10), new java.sql.Date(date.getTime()),new java.sql.Date(0));
        employeeDao.addEmployee(e);
    }
}
