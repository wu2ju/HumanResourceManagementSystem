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
        // 登录：先查看cookie中是否有次记录，
        // 登录时，只需要账号和密码，不需要判断类型type，因为账号是唯一的，且管理员的账号是已经存在表中的，不能被注册
        if (e == null || e.geteAccount() == null || e.getePassword() == null || e.geteType() == null){
            model.addAttribute("str","填写信息不完整，请重新输入！");
            return "login";
        }
        Employee employee = employeeBiz.login(e);
        if (employee == null){
            model.addAttribute("str","账号或密码错误！");
            return "login";
        }
        if (remember != null){
            //表示要将账号和密码存进Cookie中，管理员和员工是一个表，账号不重复
            Cookie cookie = new Cookie(e.geteAccount(),e.getePassword());
            cookie.setMaxAge(10*60);
            response.addCookie(cookie);
        }
        session.setAttribute("e",employee);
        if (employee.geteType() == 0){
            // 0表示管理员登录，进入管理员界面，1表示员工登录
            return "admin";
        }else {
            return "employee";
        }

        /*if (type.equals("0")){
            // 0表示管理员登录，进入管理员界面，1表示员工登录
            Admin a = adminBiz.login(admin);
            if (a == null){
                model.addAttribute("str","账号或密码错误！");
                return "login";
            }
            session.setAttribute("ad",a);
            return "admin";
        }else {
            //根据账号和密码查询出对应的员工
            Employee e = employeeBiz.login(new Employee(admin.getAdAccount(),admin.getAdPassword()));
            if (e == null){
                model.addAttribute("str","账号或密码错误！");
                return "login";
            }
            session.setAttribute("e",e);
            return "employee";
        }*/

    }

    @RequestMapping("hasCookie")
    public String hasCookie( Cookie[] cookies, HttpSession session){
        // 登录：先查看cookie中是否有次记录，
        if (cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String account = cookie.getName();
                String password = cookie.getValue();

                Employee e = employeeBiz.login(new Employee(account, password));
                if (e != null){
                    // Cookie中找到了用户那就登录成功
                    session.setAttribute("e",e);
                    if (e.geteType() == 0){
                        // 0表示管理员登录，进入管理员界面，1表示员工登录
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
