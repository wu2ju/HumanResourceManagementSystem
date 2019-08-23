package com.wuju.controller;

import com.wuju.biz.AdminBiz;
import com.wuju.biz.EmployeeBiz;
import com.wuju.model.Admin;
import com.wuju.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Resource
    private AdminBiz adminBiz;
    @Resource
    private EmployeeBiz employeeBiz;

    @RequestMapping("login")
    public String login(Employee e, String remember, Model model, HttpServletResponse response, HttpSession session){
        // ��¼���Ȳ鿴cookie���Ƿ��дμ�¼��
        // ��¼ʱ��ֻ��Ҫ�˺ź����룬����Ҫ�ж�����type����Ϊ�˺���Ψһ�ģ��ҹ���Ա���˺����Ѿ����ڱ��еģ����ܱ�ע��
        if (e == null || e.geteAccount() == null || e.getePassword() == null || e.geteType() == null){
            model.addAttribute("str","��д��Ϣ�����������������룡");
            return "login";
        }
        Employee employee = employeeBiz.login(e);
        if (employee == null){
            model.addAttribute("str","�˺Ż��������");
            return "login";
        }
        if (remember != null){
            //��ʾҪ���˺ź�������Cookie�У�����Ա��Ա����һ�����˺Ų��ظ�
            Cookie cookie = new Cookie(e.geteAccount(),e.getePassword());
            cookie.setMaxAge(10*60);
            response.addCookie(cookie);
        }
        session.setAttribute("e",employee);
        if (employee.geteType() == 0){
            // 0��ʾ����Ա��¼���������Ա���棬1��ʾԱ����¼
            return "admin";
        }else {
            return "employee";
        }

        /*if (type.equals("0")){
            // 0��ʾ����Ա��¼���������Ա���棬1��ʾԱ����¼
            Admin a = adminBiz.login(admin);
            if (a == null){
                model.addAttribute("str","�˺Ż��������");
                return "login";
            }
            session.setAttribute("ad",a);
            return "admin";
        }else {
            //�����˺ź������ѯ����Ӧ��Ա��
            Employee e = employeeBiz.login(new Employee(admin.getAdAccount(),admin.getAdPassword()));
            if (e == null){
                model.addAttribute("str","�˺Ż��������");
                return "login";
            }
            session.setAttribute("e",e);
            return "employee";
        }*/

    }

    @RequestMapping("hasCookie")
    public String hasCookie( Cookie[] cookies, HttpSession session){
        // ��¼���Ȳ鿴cookie���Ƿ��дμ�¼��
        if (cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String account = cookie.getName();
                String password = cookie.getValue();

                Employee e = employeeBiz.login(new Employee(account, password));
                if (e != null){
                    // Cookie���ҵ����û��Ǿ͵�¼�ɹ�
                    session.setAttribute("e",e);
                    if (e.geteType() == 0){
                        // 0��ʾ����Ա��¼���������Ա���棬1��ʾԱ����¼
                        return "admin";
                    }else {
                        return "employee";
                    }
                }
            }
        }
        return "login";
    }

}
