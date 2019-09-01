import com.wuju.controller.EmployeeController;
import com.wuju.controller.UserController;
import com.wuju.dao.ResumeDao;
import com.wuju.dao.ResumeForIVDao;
import com.wuju.model.Resume;
import com.wuju.model.ResumeForIV;
import com.wuju.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class StaticMethodTest {

    @Test
    public void testBeforeOneMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        System.out.println("year: " + calendar.get(Calendar.YEAR));
        System.out.println("month: " + calendar.get(Calendar.MONTH));
        System.out.println("day: " + calendar.get(Calendar.DATE));
        calendar.add(Calendar.MONTH,-1);
        System.out.println("year: " + calendar.get(Calendar.YEAR));
        System.out.println("month: " + calendar.get(Calendar.MONTH));
        System.out.println("day: " + calendar.get(Calendar.DATE));
        Date date1 = calendar.getTime();

        java.sql.Date date2 = new java.sql.Date(date1.getTime());
        System.out.println(date2);
        boolean b = EmployeeController.beforeOneMonth(date2);
        System.out.println(b);
    }

}
