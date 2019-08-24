import com.wuju.dao.EmployeeDao;
import com.wuju.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class EmployeeDaoTest {
    @Resource
    private EmployeeDao employeeDao;
    @Test
    public void testGetEmployee(){
        Employee e = employeeDao.getEmployeeByAccountAndPassword(new Employee("aaa","123"));
        System.out.println(e);
    }
}
