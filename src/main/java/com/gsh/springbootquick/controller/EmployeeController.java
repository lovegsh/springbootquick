package com.gsh.springbootquick.controller;

import com.gsh.springbootquick.dao.DepartmentDao;
import com.gsh.springbootquick.dao.EmployeeDao;
import com.gsh.springbootquick.entities.Department;
import com.gsh.springbootquick.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * Create By GSH on .
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //来到添加页面之前，查出所有的部门，再页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息： "+employee);
        //来到员工列表页面   redirect: 重定向   forward: 转发
        return "redirect:/emps";
    }
}
