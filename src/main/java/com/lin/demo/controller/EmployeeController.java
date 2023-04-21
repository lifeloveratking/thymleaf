package com.lin.demo.controller;

import com.lin.demo.dao.DepartmentDao;
import com.lin.demo.dao.EmployeeDao;
import com.lin.demo.pojo.Department;
import com.lin.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @RequestMapping("/emp")
    public String emp(Model model){
        Collection<Employee> employees=employeeDao.getAllEmployees();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @RequestMapping("/toadd")
    public String toAddpage(Model model){
        Collection<Department> departments= departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    @PostMapping("/empadd")
    public String toAddEmp(Employee employee){
        //添加员工
        System.out.println("save=>"+employee);
        employeeDao.addEmployee(employee);
        return "redirect:/emp";
    }

    //去员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdate(@PathVariable("id")Integer id,Model model){
        Employee employee=employeeDao.getEmployeeByID(id);
        model.addAttribute("emp",employee);
        Collection<Department> departments= departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }
    @PostMapping("/updateEmp")
    public String update(Employee employee){
        employeeDao.addEmployee(employee);
        return "redirect:/emp";
    }
    //删除员工
    @GetMapping("/daleteEmp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.deleteEmployeeByID(id);
        return "redirect:/emp";
    }
}
