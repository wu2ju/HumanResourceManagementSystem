import com.wuju.dao.RewardPunishDao;
import com.wuju.dao.TrainDao;
import com.wuju.model.Page;
import com.wuju.model.RewardPunish;
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
public class RewardPunishDaoTest {
    @Resource
    private RewardPunishDao rewardPunishDao;

    @Test
    public void testGetRewardPunishMonth(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("month",9);
        map.put("start",1);
        map.put("end",3);
        List<RewardPunish> rewardPunishes = rewardPunishDao.getRewardPunishMonthByLimit(map);
        System.out.println(rewardPunishes);
        int countMonth = rewardPunishDao.getRewardPunishCountMonth(9);
        System.out.println(countMonth);
    }
}
