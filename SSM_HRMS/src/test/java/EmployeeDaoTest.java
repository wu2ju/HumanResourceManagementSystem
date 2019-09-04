import com.wuju.dao.DepartmentDao;
import com.wuju.dao.EmployeeDao;
import com.wuju.dao.PositionDao;
import com.wuju.dao.ResumeDao;
import com.wuju.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class EmployeeDaoTest {
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private ResumeDao resumeDao;
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private PositionDao positionDao;

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

    @Test
    public void testGetEmployeeBydpName(){
        Department department = departmentDao.getDepartmentByDpName("销售部");
        List<Position> positions = positionDao.getPositionByDpId(department.getDpId());
        List<Employee> employees = new ArrayList<>();
        if (positions != null && positions.size() > 0) {
            for (Position p : positions) {
                List<Employee> employees1 = employeeDao.getEmployeeBypId(p.getpId());
                if (employees1 != null && employees1.size() > 0){
                    employees.addAll(employees1);
                }
            }
        }
        System.out.println("职位： " + positions);
        System.out.println("员工： " + employees);
        System.out.println(employees.size());
    }

    @Test
    public void testGetEmployeeByLimit(){
        int pageNo =1;
        Page page=new Page<>();
        int totalRows = employeeDao.getAllEmployeesCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Employee> employees = employeeDao.getAllEmployeesByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(employees);
        System.out.println(page);
    }

}
