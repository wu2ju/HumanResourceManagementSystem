import com.wuju.controller.UserController;
import com.wuju.dao.InterviewDao;
import com.wuju.dao.ResumeDao;
import com.wuju.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class InterviewDaoTest {
    @Resource
    private InterviewDao interviewDao;
    @Test
    public void testGetInterview(){
        Page page=new Page<>();
        int uId = 1;
        int pageNo = 1;
        int totalRows = interviewDao.getInterviewCountByuId(uId);
        HashMap<String,Object> map = new HashMap<>();
        map.put("uId",uId);
        map.put("start",(pageNo-1)*page.getPageSize() + 1);
        map.put("end",pageNo * page.getPageSize());
        List<Interview> interviews = interviewDao.getInterviewByuIdAndLimit(map);
        page.setPageNo(pageNo);
        page.setTotalRows(totalRows);
        page.setList(interviews);
        System.out.println(page);
        System.out.println(page.getList());
    }

    @Test
    public void testUpdateInterview(){
        Interview interview = interviewDao.getInterviewById(5);
        System.out.println(interview);
        interview.setItState(1);
        interviewDao.updateInterview(interview);
    }
}
