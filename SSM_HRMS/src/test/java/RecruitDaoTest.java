import com.wuju.dao.PositionDao;
import com.wuju.dao.RecruitDao;
import com.wuju.model.Department;
import com.wuju.model.Position;
import com.wuju.model.Recruit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class RecruitDaoTest {
    @Resource
    private RecruitDao recruitDao;
    @Test
    public void testAddRecruit(){
        Recruit recruit = new Recruit();
        Date date = new Date();
        recruit.setRcRelease(new java.sql.Date(date.getTime()));
//        recruit.setRcDeadline(new java.sql.Date(date.getTime() + 10*60*60));
        recruit.setRcState(1);
        recruit.setRcBackout(new java.sql.Date(date.getTime() + 10*60*60));
        recruit.setDepartment(new Department(3));
        recruit.setPosition(new Position(4));
        recruitDao.addRecruit(recruit);
    }

    @Test
    public void testGetRecruit(){
        Recruit recruit = recruitDao.getRecruitById(1);
        System.out.println(recruit);
    }

    @Test
    public void testGetRecruit2(){
        Recruit recruit = recruitDao.getRecruitById(1);
        System.out.println(recruit);
    }
}