import com.wuju.dao.ResumeDao;
import com.wuju.dao.ResumeForIVDao;
import com.wuju.dao.TrainDao;
import com.wuju.model.*;
import com.wuju.util.ControllerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class TrainDaoTest {
    @Resource
    private TrainDao trainDao;

    @Test
    public void testAddTrain(){
        Train train = trainDao.getTrainById(1);
        System.out.println(train);
    }

    @Test
    public void testGetAllTrain(){
        List<Train> trains = trainDao.getAllTrains();
        System.out.println(trains);
    }

    @Test
    public void testGetTrainByLimit(){
        int pageNo = 2;
        Page page=new Page<>();
        int totalRows = trainDao.getAllTrainsCount();
        HashMap<String,Object> map = new HashMap<>();
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Train> trains = trainDao.getAllTrainsByLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(trains);
        System.out.println(page.getList());
        System.out.println(page);
    }
}
