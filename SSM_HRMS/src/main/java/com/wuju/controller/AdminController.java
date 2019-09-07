package com.wuju.controller;

import com.wuju.biz.*;
import com.wuju.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Resource
    private AdminBiz adminBiz;
    @Resource
    private EmployeeBiz employeeBiz;
    @Resource
    private CompanyBiz companyBiz;
    @Resource
    private DepartmentBiz departmentBiz;
    @Resource
    private PositionBiz positionBiz;

    /*@RequestMapping("employee")
    public String employee(Model model){
        employeeBiz.getAllEmployees();
        return "employee";
    }*/

    @RequestMapping("addPos")
    public String addPos(Position p, Department d, Model model){
        // d里面有dpName，在该dpName的部门下添加职位
        System.out.println("addPos: " + p);
        System.out.println("addPos: " + d);
        if (p.getpName().equals("") || p.getpSalary()==null || p.getpLocation().equals("") || p.getpExperience().equals("") ||
                p.getpEducation()==null || p.getpIntroduction().equals("") || p.getpRequest().equals("")){
            model.addAttribute("str","填入信息不完整");
            return "forward:position";
        }
        Department department = departmentBiz.getDepartmentByDpName(d.getDpName());
        p.setDepartment(department);
        boolean flag = positionBiz.addPosition(p);
        if (!flag){
            model.addAttribute("str","添加失败");
            return "forward:position";
        }
        return "forward:position";
    }

    @RequestMapping("delPos")
    public String delPos(Integer pId, Model model){
        // 删除部门
        boolean flag = positionBiz.delPosition(pId);
        //不管更新成功或失败
        if (!flag){
            model.addAttribute("str","职位下有在职员工，删除失败");
        }
        return "forward:position";
    }

    @RequestMapping("updatePos")
    public String updatePos(Position position, Model model){
        // 1、显示部门信息
        Position position1 = positionBiz.getPositionById(position.getpId());
        position.setDepartment(position1.getDepartment());
        boolean flag = positionBiz.updatePosition(position);
        //不管更新成功或失败
        if (flag){
            model.addAttribute("str","修改成功");
        }else {
            model.addAttribute("str","职位重名，修改失败");
        }
        return "forward:position";
    }

    @RequestMapping("position")
    public String position(Integer pageNo, Department d,  Model model){
        // 三个入口，1、d不为null，但是所有属性为null 2、d里面只有dpId 3、d里面只有dpName 查找该dpId/dpName的部门职位
        // 显示部门信息
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
//        List<Position> positions = new ArrayList<>();
        Page<Position> positionPage = new Page<>();
        System.out.println("position: " + d.getDpName());
        if (d.getDpId() == null && (d.getDpName() == null || d.getDpName().equals("") ) ){
            //select 选择部门时，dpName可能为""。
//            positions = positionBiz.getAllPositions();
            positionPage = positionBiz.getAllPositionsByLimit(pageNo);
        }else {
            Department department = departmentBiz.getDepartment(d);
            System.out.println("position: " + department);
            //Department中包含Company和Position
            //Position又包含Employee
            if (department != null){
//                positions = department.getPositions();
                positionPage = positionBiz.getPositionsByDpIdAndLimit(department.getDpId(),pageNo);
            }
        }
//        model.addAttribute("positions",positions);
        model.addAttribute("positionPage",positionPage);
        return "position";
    }

    @RequestMapping("addDep")
    public String addDep(Department d, Model model){
        // 增加部门
        if (d.getDpName().equals("")){
            model.addAttribute("str","填写信息不完整");
            return "forward:department";
        }
        boolean flag = departmentBiz.addDepartment(d);
        //不管更新成功或失败
        if (!flag){
            model.addAttribute("str","添加失败");
        }
        return "forward:department";
    }

    @RequestMapping("delDep")
    public String delDep(Integer dpId, Model model){
        // 删除部门
        boolean flag = departmentBiz.delDepartment(dpId);
        //不管更新成功或失败
        if (!flag){
            model.addAttribute("str","部门下有在职员工，删除失败");
        }
        return "forward:department";
    }


    @RequestMapping("updateDep")
    public String updateDep(Department department, Model model){
        // 1、显示部门信息
        boolean flag = departmentBiz.updateDepartment(department);
        //不管更新成功或失败
        if (flag){
            model.addAttribute("str","修改成功");
        }else {
            model.addAttribute("str","部门重名，修改失败");
        }
        return "forward:department";
    }

    @RequestMapping("department")
    public String department(Integer pageNo, Model model){
        // 1、显示部门信息
//        List<Department> departments = departmentBiz.getAllDepartments();
//        model.addAttribute("departments",departments);

        if(pageNo == null || pageNo < 1){
            pageNo=1;
        }
        Page<Department> departmentPage = departmentBiz.getAllDepartmentsByLimit(pageNo);
        model.addAttribute("departmentPage",departmentPage);
        return "department";
    }

    @RequestMapping("companyInfo")
    public String companyInfo(Company c, Model model){
        // 1、显示公司信息 2、更新公司信息
        if (c.getCpPhone() != null){
            companyBiz.updateCompany(c);
        }
//        c = companyBiz.getCompanyByName(c.getCpName());
        c = companyBiz.getCompanyById(1);
        model.addAttribute("company",c);
        return "companyInfo";
    }

    @RequestMapping("login")
    public String login(Employee e ,/*String eAccount,String ePassword, */String remember, Model model, HttpServletResponse response, HttpSession session){
        // 登录：先查看cookie中是否有次记录，
        // 登录时，只需要账号和密码，不需要判断类型type，因为账号是唯一的，且管理员的账号是已经存在表中的，不能被注册
//        Employee e = new Employee(eAccount,ePassword);
        if (e == null || e.geteAccount().equals("") || e.getePassword().equals("")){
            model.addAttribute("str","填写信息不完整，请重新输入！");
            return "login";
        }
        Employee employee = employeeBiz.login(e);
        if (employee == null){
            model.addAttribute("str","账号或密码错误！");
            return "login";
        }
        if (employee.geteState() != null && employee.geteState() == 2){
            model.addAttribute("str","离职员工不能登录！");
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
            //获取培训通知，刚（toState=1）发布（trState=2）给员工（employee.geteId()）的培训
            Employee employeeWithTrain = employeeBiz.getEmployeeByeIdAndToStateAndTrState(employee.geteId(), 1, 2);
            session.setAttribute("employeeWithTrain",employeeWithTrain);
            return "redirect:employee";
        }
    }

    @RequestMapping("hasCookie")
    public String hasCookie(HttpServletRequest request, HttpSession session, Model model){
        // 登录：先查看cookie中是否有次记录，
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length>0){
            for (Cookie cookie : cookies) {
                String account = cookie.getName();
                String password = cookie.getValue();

                Employee e = employeeBiz.login(new Employee(account, password));
                if (e != null){
                    // Cookie中找到了用户那就登录成功
                    if (e.geteState() == 2){
                        model.addAttribute("str","离职员工不能登录！");
                        return "login";
                    }
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
