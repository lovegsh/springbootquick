package com.gsh.springbootquick.controller;

import com.gsh.springbootquick.dao.DepartmentDao;
import com.gsh.springbootquick.dao.EmployeeDao;
import com.gsh.springbootquick.entities.Department;
import com.gsh.springbootquick.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
        System.out.println("添加的员工信息： "+employee);
        employeeDao.save(employee);
        //来到员工列表页面   redirect: 重定向   forward: 转发
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工信息，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        //显示所有部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面（和添加页面公用）
        return "emp/add";
    }

    //员工修改
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("员工修改的数据： "+employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }


}
