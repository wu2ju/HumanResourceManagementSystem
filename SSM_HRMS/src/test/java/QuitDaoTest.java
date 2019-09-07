import com.wuju.dao.QuitDao;
import com.wuju.dao.TrainDao;
import com.wuju.model.Page;
import com.wuju.model.Quit;
import com.wuju.model.Train;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class QuitDaoTest {
    @Resource
    private QuitDao quitDao;

    @Test
    public void testGetQuitMonth(){
        Quit quit = quitDao.getQuitMonth(3);
        System.out.println(quit);
    }

}
