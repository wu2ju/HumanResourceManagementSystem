package com.wuju.controller;

import com.wuju.biz.*;
import com.wuju.model.*;
import com.wuju.util.ControllerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class UserController {
    @Resource
    private RecruitBiz recruitBiz;
    @Resource
    private DepartmentBiz departmentBiz;
    @Resource
    private UserBiz userBiz;
    @Resource
    private ResumeBiz resumeBiz;
    @Resource
    private InterviewBiz interviewBiz;
    @Resource
    private EmployeeBiz employeeBiz;
    @Resource
    private NotificationBiz notificationBiz;
    @Resource
    private ResumeForIVBiz resumeForIVBiz;

    @RequestMapping("resumeForIV")
    public String resumeForIV(Integer rId, Model model){
        ResumeForIV resumeForIV = resumeForIVBiz.getResumeForIVById(rId);
        model.addAttribute("resumeForIV",resumeForIV);
        return "resumeForIV";
    }

    @RequestMapping("updateInterview")
    public String updateInterview(Interview interview, String itTime1, Integer eId, HttpSession session, Model model){
        // 更新面试记录的状态
//        面试的状态，0 投递简历、等面试通知；1 管理员拒绝； 2 管理员查看简历、通知面试； 3 用户接受面试； 4 用户拒绝面试；
//         5 面试通过，安排入职 6 面试没通过
        Interview it = interviewBiz.getInterviewById(interview.getItId());
        it.setItState(interview.getItState());
        if (interview.getItState() == 2){
            System.out.println("updateInterview: " + interview + ", " + itTime1 + ", " + eId);
            if (itTime1 == null || itTime1.equals("")){
                return "redirect:adminInterview";
            }
            String iTtime = itTime1.replace("T", " ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = new Date();
            try {
                 date = sdf.parse(iTtime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date.before(new Date())){
                return "redirect:adminInterview";
            }
            // 设置面试官和面试时间、地点
            it.setItTime(new Timestamp(date.getTime()));
            it.setItAddress(interview.getItAddress());
            if (eId != null){
                it.setEmployee(employeeBiz.getEmployeeById(eId));
            }
        }
        if (interview.getItState() == 5){
            // 面试通过需要给用户发送账号和密码，存在新建的通知表里
            User user = it.getUser();
            Notification notification = new Notification();
            notification.setNtAccount(user.getuPhone());
            notification.setNtPassword("123");
            notification.setUser(user);
            notification.setNtState(1);
            notificationBiz.addNotification(notification);
            // 添加员工，且得到相应的信息
            Resume r = resumeBiz.getResumeByuId(user.getuId()).get(0);
            Date date = new Date();
            Employee e = new Employee(user.getuPhone(),"123",1,r.getrName(),r.getrSex(),r.getrBirthday(),
                    user.getuPhone(),user.getuPhone()+"@qq.com",0,it.getRecruit().getPosition(),
                    new java.sql.Date(date.getTime()), new java.sql.Date(0));
            employeeBiz.addEmployee(e);
        }
        interviewBiz.updateInterview(it);
        User user = (User) session.getAttribute("u");
        if (user == null){
            return "redirect:adminInterview";
        }else {
            return "redirect:userInterview";
        }
    }

    @RequestMapping("userInterview")
    public String userInterview(Integer pageNo, HttpSession session, Model model){
        // 如果是用户，只看到该用户投递简历的面试记录
        //eType为0的是管理员，eType为1的是普通员工
        User user = (User) session.getAttribute("u");
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        if (user != null){
            Notification nt1 = notificationBiz.getNotificationByuIdAndNtState(user.getuId(), 1);
            System.out.println("userInterview: " + nt1);
            if (nt1 != null){
                nt1.setNtState(2);
                notificationBiz.updateNotification(nt1);
                session.removeAttribute("nt");
            }
            //表示用户
            Page<Interview> interviewPage = interviewBiz.getInterviewByuIdAndLimit(user.getuId(), pageNo);
            model.addAttribute("interviewPage",interviewPage);
            if (interviewPage.getList() != null && interviewPage.getList().size()>0 ){
                for (Interview interview : interviewPage.getList()) {
                    if (interview.getItState() == 5){
                        Notification nt = notificationBiz.getNotificationByuId(user.getuId());
                        if (nt != null){
                            model.addAttribute("str","恭喜入职"+interview.getRecruit().getPosition().getpName()+
                                    "，员工账号：" + nt.getNtAccount() + "，员工密码：" + nt.getNtPassword());
                        }
                    }
                }
            }

        }
//        面试的状态，0 投递简历、等面试通知；1 管理员拒绝； 2 管理员查看简历、通知面试； 3 用户接受面试； 4 用户拒绝面试；
//         5 面试通过，安排入职 6 面试没通过
        return "userInterview";
    }

    @RequestMapping("adminInterview")
    public String adminInterview(Integer pageNo, Integer itState, HttpSession session, Model model){
        // 如果是管理员，看到所有用户投递简历的面试记录
        // 如果是用户，只看到该用户投递简历的面试记录
        Employee employee = (Employee) session.getAttribute("e");
        //eType为0的是管理员，eType为1的是普通员工
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        if (employee != null && employee.geteType()==0){
            //表示管理员，
            Page<Interview> interviewPage = new Page<>();
            if (itState == null){
                interviewPage = interviewBiz.getAllInterviewsByLimit(pageNo);
            }else {
                interviewPage = interviewBiz.getInterviewByStateAndLimit(itState, pageNo);
            }
            model.addAttribute("interviewPage",interviewPage);
            HashMap<Integer, String> chooseItState = new HashMap<>();
            chooseItState.put(1,"不面试");
            chooseItState.put(2,"已通知面试");
            chooseItState.put(3,"用户接受面试");
            chooseItState.put(4,"用户拒绝面试");
            chooseItState.put(5,"面试通过");
            chooseItState.put(6,"面试没通过");
            model.addAttribute("chooseItState",chooseItState);
            // 插入所选职位的所有员工，来选择面试官
            List<Interview> interviews = interviewPage.getList();
            HashMap<Integer, List<Employee>> employeeMap = new HashMap<>();
            if (interviews != null && interviews.size() > 0){
                for (Interview interview : interviews) {
                    List<Employee> employees1 = employeeBiz.getEmployeeBypId(interview.getRecruit().getPosition().getpId());
                    employeeMap.put(interview.getItId(),employees1);
                }
            }
            model.addAttribute("employeeMap",employeeMap);
        }
//        面试的状态，0 投递简历、等面试通知；1 管理员拒绝； 2 管理员查看简历、通知面试； 3 用户接受面试； 4 用户拒绝面试；
//         5 面试通过，安排入职 6 面试没通过
        return "adminInterview";
    }

    @RequestMapping("addInterview")
    public String addInterview(Integer rcId,Integer rId, Model model){
        // 投递简历之后，在面试简历里面生成一条记录，并生成面试记录，面试的状态为0 投递简历待选择
        if (rId == null){
            //如果没有选中简历，表示没有简历，那么跳转到完善简历
            return "forward:resume";
        }
        Date date = new Date();
        java.sql.Date itDeliveryResume = new java.sql.Date(date.getTime());
        Timestamp itTime = new Timestamp(0);
        Resume resume = resumeBiz.getResumeById(rId);
        // 找到过去的简历，生成新的用于面试的简历
        ResumeForIV resumeForIV = new ResumeForIV();
        ControllerUtil.transferAttributeValues(resume, resumeForIV); //传递属性值，生成一个用于面试的简历
        System.out.println("addInterview: " + resume);
        System.out.println("addInterview: " + resumeForIV);
        // 一个招聘岗位，一个用户只能投一次简历
        Interview interview1 = interviewBiz.getInterviewByrcIdAnduId(rcId, resume.getUser().getuId());
        if (interview1 != null){
            model.addAttribute("str","投递简历失败，一个招聘岗位只能投递一次简历");
            return "forward:recruitment";
        }
        resumeForIVBiz.addResumeForIV(resumeForIV);
        Interview interview = new Interview(new Recruit(rcId),resumeForIV,itDeliveryResume,0,itTime," ",new Employee(1),resume.getUser());
        // 默认面试官为管理员
        interviewBiz.addInterview(interview);
        return "forward:userInterview";
    }


    @RequestMapping("updateResume")
    public String updateResume(Resume resume){
        // 更新简历信息
        Resume resume1 = resumeBiz.getResumeById(resume.getrId());
        Class<? extends Resume> resumeClass = resume.getClass();
        Method[] methods = resumeClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get")){
                try {
                    Object field = method.invoke(resume);
                    if (field != null){
                        for (Method method1 : methods) {
                            if (method1.getName().startsWith("set") && method1.getName().substring(3).equals(method.getName().substring(3))){
                                //找到对应的set方法
                                method1.invoke(resume1,field);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        resumeBiz.updateResume(resume1);
        return "forward:resume";
    }

    @RequestMapping("resume")
    public String resume(Integer rId, String method, Model model, HttpSession session){
        // 如果有简历信息，那么显示简历信息，如果没有，那生成一份空的简历，用来填写
        // 完善简历信息，添加、查看、修改、删除
        if (method != null){
            if (method.equals("添加")){
                resumeBiz.addResume(resumeBiz.getResumeById(rId));
            }else if (method.equals("删除")){
                resumeBiz.delResume(rId);
            }
        }
        User user = (User) session.getAttribute("u");
        //已经做过登录拦截器设置，user肯定不为空。
        List<Resume> resumes = resumeBiz.getResumeByuId(user.getuId());
        model.addAttribute("resumes",resumes);
        if (resumes != null && resumes.size()>0){
            Resume resume = resumes.get(0);
            if (rId != null){
                // 根据选择的简历来显示
                resume = resumeBiz.getResumeById(rId);
            }
            model.addAttribute("resume",resume);
            return "resume";
        }
        java.sql.Date o = new java.sql.Date(0);
        Resume resume = new Resume(" "," ",o,o," "," "," ",o,o," "," "," "," ",o,o," ",0," "," "," ",new User(1));
        resumeBiz.addResume(resume);
        List<Resume> resumesNew = resumeBiz.getResumeByuId(user.getuId());
        Resume resume1 = resumesNew.get(0);  //为了得到含有rId和uId的简历
        model.addAttribute("resume",resume1);
        return "resume";
    }

    @RequestMapping("recruitDetails")
    public String recruitDetails(Integer rcId,HttpSession session, Model model){
        // 点击招聘信息，获取该招聘信息的详细信息，显示在recruitDetails.jsp中,此页面可以投递简历
        //还有简历信息，用户选择简历，投递到该职位
        Recruit recruit = recruitBiz.getRecruitById(rcId);
        User user = (User) session.getAttribute("u");
        //已经做过登录拦截器设置，user肯定不为空。
        List<Resume> resumes = new ArrayList<>();
        if (user != null){
            resumes = resumeBiz.getResumeByuId(user.getuId());
        }
        model.addAttribute("recruit",recruit);
        model.addAttribute("resumes",resumes);
        return "recruitDetails";
    }

    @RequestMapping("firstRegister")
    public String firstRegister(){
        //只是为了做页面跳转
        return "register";
    }

    @RequestMapping("registerName")
    @ResponseBody
    public String registerName(String uName){
        // 确认注册时，是否已经重名
//        System.out.println("registerName: " + uName);
        User user = userBiz.getUserByuName(uName);
        if (user != null){
            return "false";
        }
        return "success";
    }

    @RequestMapping("register")
    public String register(User u ,Model model, HttpSession session){
        // 注册：
        if (u == null || u.getuName().equals("") || u.getuPass().equals("") || u.getuPhone().equals("")){
            model.addAttribute("str","填写信息不完整，请重新输入！");
            return "register";
        }
        userBiz.register(u);
        User user = userBiz.login(u);
        session.setAttribute("u",user);
        return "forward:recruitment";
    }

    @RequestMapping("userLogin")
    public String userLogin(User u ,String remember, Model model, HttpServletResponse response, HttpSession session){
        // 登录：先查看cookie中是否有次记录，
        // 登录时，只需要账号和密码
        System.out.println("userLogin： " + u);
        if (u == null || u.getuName().equals("") || u.getuPass().equals("")){
            model.addAttribute("str","填写信息不完整，请重新输入！");
            return "userLogin";
        }
        User user = userBiz.login(u);
        if (user == null){
            model.addAttribute("str","账号或密码错误！");
            return "userLogin";
        }
        if (remember != null){
            //表示要将账号和密码存进Cookie中
            Cookie cookie = new Cookie(u.getuName(),u.getuPass());
            cookie.setMaxAge(10*60);
            response.addCookie(cookie);
        }
        session.setAttribute("u",user);
        // 获取用户的面试结果通知
        Notification nt = notificationBiz.getNotificationByuIdAndNtState(user.getuId(), 1);
        if (nt != null){
            session.setAttribute("nt",nt);
        }
        return "forward:recruitment";
    }

    @RequestMapping("userHasCookie")
    public String userHasCookie(HttpServletRequest request, HttpSession session){
        // 登录：先查看cookie中是否有次记录，
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String pass = cookie.getValue();

                User user = userBiz.login(new User(name, pass));
                if (user != null){
                    // Cookie中找到了用户那就登录成功
                    session.setAttribute("u",user);
                    return "forward:recruitment";
                }
            }
        }
        return "userLogin";
    }

    @RequestMapping("recruitment")
    public String recruit(Integer pageNo, String dpName, Model model){
        // 这是给用户看的招聘信息，点击投递简历，
        // 两个个入口，1、rc不为null，但是所有属性为null 2、rc里面只有rcState 查找rcState状态的职位
        // 显示部门信息，用来二级联动，选择职位
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Recruit> recruitPage = new Page<>();
        if (dpName == null || dpName.equals("")){
            //select 选择部门时，dpName可能为""。
            //只选择已发布的招聘信息
            recruitPage = recruitBiz.getRecruitsByRcStateAndLimit(2,pageNo);
        }else {
            Department department = departmentBiz.getDepartmentByDpName(dpName);
            //Department中包含Company和Position
            if (department != null){
                recruitPage = recruitBiz.getRecruitsByDpIdAndStateAndLimit(department.getDpId(), pageNo);
            }
        }
        model.addAttribute("recruitPage",recruitPage);
        return "recruitment";
    }

}
