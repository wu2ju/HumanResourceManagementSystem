import com.wuju.dao.InterviewDao;
import com.wuju.dao.NotificationDao;
import com.wuju.model.Interview;
import com.wuju.model.Notification;
import com.wuju.model.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class NotificationDaoTest {
    @Resource
    private NotificationDao notificationDao;
    @Test
    public void testGetNotification(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("uId",1);
        map.put("ntState",1);
        Notification nt = notificationDao.getNotificationByuIdAndNtState(map);
        System.out.println(nt);

    }


}
