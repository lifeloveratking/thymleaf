package com.lin.demo.controller;

import com.lin.demo.dao.EmployeeDao;
import com.lin.demo.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String index(){
        return "login";
    }

    @RequestMapping("/user/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model,
            HttpSession session){
        if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/dashboard";
        }else {
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }
    @RequestMapping("/404")
    public String notfound(){
        return "404";
    }

    @RequestMapping("/dashboard")
    public String dash(){
        return "dashboard";
    }
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }

}
