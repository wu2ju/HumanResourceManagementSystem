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

    @RequestMapping("employee")
    public String employee(Model model){
        employeeBiz.getAllEmployees();
        return "employee";
    }

    @RequestMapping("addPos")
    public String addPos(Position position, Department d, Model model){
        // d������dpName���ڸ�dpName�Ĳ��������ְλ
        System.out.println("addPos: " + position);
        System.out.println("addPos: " + d);
        Department department = departmentBiz.getDepartmentByDpName(d.getDpName());
        position.setDepartment(department);
        boolean flag = positionBiz.addPosition(position);
        if (!flag){
            model.addAttribute("flag",flag);
        }
        return "forward:position";
    }

    @RequestMapping("delPos")
    public String delPos(Integer pId, Model model){
        // ɾ������
        boolean flag = positionBiz.delPosition(pId);
        //���ܸ��³ɹ���ʧ��
        if (!flag){
            model.addAttribute("str","ְλ������ְԱ����ɾ��ʧ��");
        }
        return "forward:position";
    }

    @RequestMapping("updatePos")
    public String updatePos(Position position, Model model){
        // 1����ʾ������Ϣ
        boolean flag = positionBiz.updatePosition(position);
        //���ܸ��³ɹ���ʧ��
        if (flag){
            model.addAttribute("str","�޸ĳɹ�");
        }else {
            model.addAttribute("str","ְλ�������޸�ʧ��");
        }
        return "forward:position";
    }

    @RequestMapping("position")
    public String position(Department d, Model model){
        // ������ڣ�1��d��Ϊnull��������������Ϊnull 2��d����ֻ��dpId 3��d����ֻ��dpName ���Ҹ�dpId/dpName�Ĳ���ְλ
        // ��ʾ������Ϣ
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        List<Position> positions = new ArrayList<>();
        System.out.println("position: " + d);
        if (d.getDpId() == null && d.getDpName() == null){
            positions = positionBiz.getAllPositions();
        }else {
            Department department = departmentBiz.getDepartment(d);
            System.out.println("position: " + department);
            //Department�а���Company��Position
            //Position�ְ���Employee
            if (department != null){
                positions = department.getPositions();
            }
        }
        System.out.println("position: " + positions);
        model.addAttribute("positions",positions);
        return "position";
    }

    @RequestMapping("addDep")
    public String addDep(Department d, Model model){
        // ���Ӳ���
        boolean flag = departmentBiz.addDepartment(d);
        //���ܸ��³ɹ���ʧ��
        if (!flag){
            model.addAttribute("flag",flag);
        }
        return "forward:department";
    }

    @RequestMapping("delDep")
    public String delDep(Integer dpId, Model model){
        // ɾ������
        boolean flag = departmentBiz.delDepartment(dpId);
        //���ܸ��³ɹ���ʧ��
        if (!flag){
            model.addAttribute("str","����������ְԱ����ɾ��ʧ��");
        }
        return "forward:department";
    }


    @RequestMapping("updateDep")
    public String updateDep(Department department, Model model){
        // 1����ʾ������Ϣ
        boolean flag = departmentBiz.updateDepartment(department);
        //���ܸ��³ɹ���ʧ��
        if (flag){
            model.addAttribute("str","�޸ĳɹ�");
        }else {
            model.addAttribute("str","�����������޸�ʧ��");
        }
        return "forward:department";
    }

    @RequestMapping("department")
    public String department(Model model){
        // 1����ʾ������Ϣ
        List<Department> departments = departmentBiz.getAllDepartments();
        model.addAttribute("departments",departments);
        return "department";
    }

    @RequestMapping("companyInfo")
    public String companyInfo(Company c, Model model){
        // 1����ʾ��˾��Ϣ 2�����¹�˾��Ϣ
        if (c.getCpPhone() != null){
            companyBiz.updateCompany(c);
        }
        c = companyBiz.getCompanyByName(c.getCpName());
        model.addAttribute("company",c);
        return "companyInfo";
    }

    @RequestMapping("login")
    public String login(Employee e ,/*String eAccount,String ePassword, */String remember, Model model, HttpServletResponse response, HttpSession session){
        // ��¼���Ȳ鿴cookie���Ƿ��дμ�¼��
        // ��¼ʱ��ֻ��Ҫ�˺ź����룬����Ҫ�ж�����type����Ϊ�˺���Ψһ�ģ��ҹ���Ա���˺����Ѿ����ڱ��еģ����ܱ�ע��
//        Employee e = new Employee(eAccount,ePassword);
        if (e == null || e.geteAccount() == "" || e.getePassword() == ""){
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
    public String hasCookie(HttpServletRequest request, HttpSession session){
        // ��¼���Ȳ鿴cookie���Ƿ��дμ�¼��
        Cookie[] cookies = request.getCookies();
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
