import com.wuju.dao.TrainDao;
import com.wuju.model.Train;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class TimeTest {

    @Test
    public void testTimestamp(){
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());
        Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());
        System.out.println("timestamp: " + timestamp);
        calendar.add(Calendar.HOUR,2);
        Timestamp timestamp1 = new Timestamp(calendar.getTimeInMillis());
        System.out.println("timestamp1: " + timestamp1);
        double hour = (timestamp.getTime()-timestamp1.getTime())/1000/3600;
        System.out.println("hour: " + hour);

    }

}
