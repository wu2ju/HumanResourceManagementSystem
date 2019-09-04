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
        // �������Լ�¼��״̬
//        ���Ե�״̬��0 Ͷ�ݼ�����������֪ͨ��1 ����Ա�ܾ��� 2 ����Ա�鿴������֪ͨ���ԣ� 3 �û��������ԣ� 4 �û��ܾ����ԣ�
//         5 ����ͨ����������ְ 6 ����ûͨ��
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
            // �������Թٺ�����ʱ�䡢�ص�
            it.setItTime(new Timestamp(date.getTime()));
            it.setItAddress(interview.getItAddress());
            if (eId != null){
                it.setEmployee(employeeBiz.getEmployeeById(eId));
            }
        }
        if (interview.getItState() == 5){
            // ����ͨ����Ҫ���û������˺ź����룬�����½���֪ͨ����
            User user = it.getUser();
            Notification notification = new Notification();
            notification.setNtAccount(user.getuPhone());
            notification.setNtPassword("123");
            notification.setUser(user);
            notification.setNtState(1);
            notificationBiz.addNotification(notification);
            // ���Ա�����ҵõ���Ӧ����Ϣ
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
        // ������û���ֻ�������û�Ͷ�ݼ��������Լ�¼
        //eTypeΪ0���ǹ���Ա��eTypeΪ1������ͨԱ��
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
            //��ʾ�û�
            Page<Interview> interviewPage = interviewBiz.getInterviewByuIdAndLimit(user.getuId(), pageNo);
            model.addAttribute("interviewPage",interviewPage);
            if (interviewPage.getList() != null && interviewPage.getList().size()>0 ){
                for (Interview interview : interviewPage.getList()) {
                    if (interview.getItState() == 5){
                        Notification nt = notificationBiz.getNotificationByuId(user.getuId());
                        if (nt != null){
                            model.addAttribute("str","��ϲ��ְ"+interview.getRecruit().getPosition().getpName()+
                                    "��Ա���˺ţ�" + nt.getNtAccount() + "��Ա�����룺" + nt.getNtPassword());
                        }
                    }
                }
            }

        }
//        ���Ե�״̬��0 Ͷ�ݼ�����������֪ͨ��1 ����Ա�ܾ��� 2 ����Ա�鿴������֪ͨ���ԣ� 3 �û��������ԣ� 4 �û��ܾ����ԣ�
//         5 ����ͨ����������ְ 6 ����ûͨ��
        return "userInterview";
    }

    @RequestMapping("adminInterview")
    public String adminInterview(Integer pageNo, Integer itState, HttpSession session, Model model){
        // ����ǹ���Ա�����������û�Ͷ�ݼ��������Լ�¼
        // ������û���ֻ�������û�Ͷ�ݼ��������Լ�¼
        Employee employee = (Employee) session.getAttribute("e");
        //eTypeΪ0���ǹ���Ա��eTypeΪ1������ͨԱ��
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        if (employee != null && employee.geteType()==0){
            //��ʾ����Ա��
            Page<Interview> interviewPage = new Page<>();
            if (itState == null){
                interviewPage = interviewBiz.getAllInterviewsByLimit(pageNo);
            }else {
                interviewPage = interviewBiz.getInterviewByStateAndLimit(itState, pageNo);
            }
            model.addAttribute("interviewPage",interviewPage);
            HashMap<Integer, String> chooseItState = new HashMap<>();
            chooseItState.put(1,"������");
            chooseItState.put(2,"��֪ͨ����");
            chooseItState.put(3,"�û���������");
            chooseItState.put(4,"�û��ܾ�����");
            chooseItState.put(5,"����ͨ��");
            chooseItState.put(6,"����ûͨ��");
            model.addAttribute("chooseItState",chooseItState);
            // ������ѡְλ������Ա������ѡ�����Թ�
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
//        ���Ե�״̬��0 Ͷ�ݼ�����������֪ͨ��1 ����Ա�ܾ��� 2 ����Ա�鿴������֪ͨ���ԣ� 3 �û��������ԣ� 4 �û��ܾ����ԣ�
//         5 ����ͨ����������ְ 6 ����ûͨ��
        return "adminInterview";
    }

    @RequestMapping("addInterview")
    public String addInterview(Integer rcId,Integer rId, Model model){
        // Ͷ�ݼ���֮�������Լ�����������һ����¼�����������Լ�¼�����Ե�״̬Ϊ0 Ͷ�ݼ�����ѡ��
        if (rId == null){
            //���û��ѡ�м�������ʾû�м�������ô��ת�����Ƽ���
            return "forward:resume";
        }
        Date date = new Date();
        java.sql.Date itDeliveryResume = new java.sql.Date(date.getTime());
        Timestamp itTime = new Timestamp(0);
        Resume resume = resumeBiz.getResumeById(rId);
        // �ҵ���ȥ�ļ����������µ��������Եļ���
        ResumeForIV resumeForIV = new ResumeForIV();
        ControllerUtil.transferAttributeValues(resume, resumeForIV); //��������ֵ������һ���������Եļ���
        System.out.println("addInterview: " + resume);
        System.out.println("addInterview: " + resumeForIV);
        // һ����Ƹ��λ��һ���û�ֻ��Ͷһ�μ���
        Interview interview1 = interviewBiz.getInterviewByrcIdAnduId(rcId, resume.getUser().getuId());
        if (interview1 != null){
            model.addAttribute("str","Ͷ�ݼ���ʧ�ܣ�һ����Ƹ��λֻ��Ͷ��һ�μ���");
            return "forward:recruitment";
        }
        resumeForIVBiz.addResumeForIV(resumeForIV);
        Interview interview = new Interview(new Recruit(rcId),resumeForIV,itDeliveryResume,0,itTime," ",new Employee(1),resume.getUser());
        // Ĭ�����Թ�Ϊ����Ա
        interviewBiz.addInterview(interview);
        return "forward:userInterview";
    }


    @RequestMapping("updateResume")
    public String updateResume(Resume resume){
        // ���¼�����Ϣ
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
                                //�ҵ���Ӧ��set����
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
        // ����м�����Ϣ����ô��ʾ������Ϣ�����û�У�������һ�ݿյļ�����������д
        // ���Ƽ�����Ϣ����ӡ��鿴���޸ġ�ɾ��
        if (method != null){
            if (method.equals("���")){
                resumeBiz.addResume(resumeBiz.getResumeById(rId));
            }else if (method.equals("ɾ��")){
                resumeBiz.delResume(rId);
            }
        }
        User user = (User) session.getAttribute("u");
        //�Ѿ�������¼���������ã�user�϶���Ϊ�ա�
        List<Resume> resumes = resumeBiz.getResumeByuId(user.getuId());
        model.addAttribute("resumes",resumes);
        if (resumes != null && resumes.size()>0){
            Resume resume = resumes.get(0);
            if (rId != null){
                // ����ѡ��ļ�������ʾ
                resume = resumeBiz.getResumeById(rId);
            }
            model.addAttribute("resume",resume);
            return "resume";
        }
        java.sql.Date o = new java.sql.Date(0);
        Resume resume = new Resume(" "," ",o,o," "," "," ",o,o," "," "," "," ",o,o," ",0," "," "," ",new User(1));
        resumeBiz.addResume(resume);
        List<Resume> resumesNew = resumeBiz.getResumeByuId(user.getuId());
        Resume resume1 = resumesNew.get(0);  //Ϊ�˵õ�����rId��uId�ļ���
        model.addAttribute("resume",resume1);
        return "resume";
    }

    @RequestMapping("recruitDetails")
    public String recruitDetails(Integer rcId,HttpSession session, Model model){
        // �����Ƹ��Ϣ����ȡ����Ƹ��Ϣ����ϸ��Ϣ����ʾ��recruitDetails.jsp��,��ҳ�����Ͷ�ݼ���
        //���м�����Ϣ���û�ѡ�������Ͷ�ݵ���ְλ
        Recruit recruit = recruitBiz.getRecruitById(rcId);
        User user = (User) session.getAttribute("u");
        //�Ѿ�������¼���������ã�user�϶���Ϊ�ա�
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
        //ֻ��Ϊ����ҳ����ת
        return "register";
    }

    @RequestMapping("registerName")
    @ResponseBody
    public String registerName(String uName){
        // ȷ��ע��ʱ���Ƿ��Ѿ�����
//        System.out.println("registerName: " + uName);
        User user = userBiz.getUserByuName(uName);
        if (user != null){
            return "false";
        }
        return "success";
    }

    @RequestMapping("register")
    public String register(User u ,Model model, HttpSession session){
        // ע�᣺
        if (u == null || u.getuName().equals("") || u.getuPass().equals("") || u.getuPhone().equals("")){
            model.addAttribute("str","��д��Ϣ�����������������룡");
            return "register";
        }
        userBiz.register(u);
        User user = userBiz.login(u);
        session.setAttribute("u",user);
        return "forward:recruitment";
    }

    @RequestMapping("userLogin")
    public String userLogin(User u ,String remember, Model model, HttpServletResponse response, HttpSession session){
        // ��¼���Ȳ鿴cookie���Ƿ��дμ�¼��
        // ��¼ʱ��ֻ��Ҫ�˺ź�����
        System.out.println("userLogin�� " + u);
        if (u == null || u.getuName().equals("") || u.getuPass().equals("")){
            model.addAttribute("str","��д��Ϣ�����������������룡");
            return "userLogin";
        }
        User user = userBiz.login(u);
        if (user == null){
            model.addAttribute("str","�˺Ż��������");
            return "userLogin";
        }
        if (remember != null){
            //��ʾҪ���˺ź�������Cookie��
            Cookie cookie = new Cookie(u.getuName(),u.getuPass());
            cookie.setMaxAge(10*60);
            response.addCookie(cookie);
        }
        session.setAttribute("u",user);
        // ��ȡ�û������Խ��֪ͨ
        Notification nt = notificationBiz.getNotificationByuIdAndNtState(user.getuId(), 1);
        if (nt != null){
            session.setAttribute("nt",nt);
        }
        return "forward:recruitment";
    }

    @RequestMapping("userHasCookie")
    public String userHasCookie(HttpServletRequest request, HttpSession session){
        // ��¼���Ȳ鿴cookie���Ƿ��дμ�¼��
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String pass = cookie.getValue();

                User user = userBiz.login(new User(name, pass));
                if (user != null){
                    // Cookie���ҵ����û��Ǿ͵�¼�ɹ�
                    session.setAttribute("u",user);
                    return "forward:recruitment";
                }
            }
        }
        return "userLogin";
    }

    @RequestMapping("recruitment")
    public String recruit(Integer pageNo, String dpName, Model model){
        // ���Ǹ��û�������Ƹ��Ϣ�����Ͷ�ݼ�����
        // ��������ڣ�1��rc��Ϊnull��������������Ϊnull 2��rc����ֻ��rcState ����rcState״̬��ְλ
        // ��ʾ������Ϣ����������������ѡ��ְλ
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Recruit> recruitPage = new Page<>();
        if (dpName == null || dpName.equals("")){
            //select ѡ����ʱ��dpName����Ϊ""��
            //ֻѡ���ѷ�������Ƹ��Ϣ
            recruitPage = recruitBiz.getRecruitsByRcStateAndLimit(2,pageNo);
        }else {
            Department department = departmentBiz.getDepartmentByDpName(dpName);
            //Department�а���Company��Position
            if (department != null){
                recruitPage = recruitBiz.getRecruitsByDpIdAndStateAndLimit(department.getDpId(), pageNo);
            }
        }
        model.addAttribute("recruitPage",recruitPage);
        return "recruitment";
    }

}
