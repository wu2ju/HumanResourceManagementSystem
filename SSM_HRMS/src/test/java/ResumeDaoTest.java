import com.wuju.controller.UserController;
import com.wuju.dao.EmployeeDao;
import com.wuju.dao.ResumeDao;
import com.wuju.dao.ResumeForIVDao;
import com.wuju.model.Employee;
import com.wuju.model.Resume;
import com.wuju.model.ResumeForIV;
import com.wuju.model.User;
import com.wuju.util.ControllerUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean.xml")
public class ResumeDaoTest {
    @Resource
    private ResumeDao resumeDao;
    @Resource
    private ResumeForIVDao resumeForIVDao;
    @Test
    public void testAddResume(){
        java.sql.Date o = new java.sql.Date(0);
        Resume resume = new Resume(" "," ",o,o," "," "," ",o,o," "," "," "," ",o,o," ",0," "," "," ",new User(1));
        /*resume.setrAdmission(origin);
        resume.setrBirthday(origin);
        resume.setrEntry(origin);
        resume.setrGraduation(origin);
        resume.setrQuit(origin);
        resume.setrWorktime(origin);*/
        System.out.println("resume: " + resume);
        resumeDao.addResume(resume);
    }

    @Test
    public void testGetResume(){
        Resume resume = resumeDao.getResumeById(8);
        System.out.println("resume: " + resume);
        ResumeForIV resumeForIV = new ResumeForIV();
        System.out.println("resumeForIV: " + resumeForIV);
        ControllerUtil.transferAttributeValues(resume,resumeForIV);

        resumeForIVDao.addResumeForIV(resumeForIV);
        System.out.println("resumeForIV: " + resumeForIV);
    }
}
