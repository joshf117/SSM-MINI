package com.mini.controller;

import com.mini.pojo.Admin;
import com.mini.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    AdminService adminService;

    //login function
    @RequestMapping("/login.action")
    public String login(String name, String pwd, HttpServletRequest request){

        Admin admin = adminService.login(name,pwd);
        if (admin != null) {
            request.setAttribute("admin",admin);
            return "main";
        } else {
            request.setAttribute("errmsg","Incorrect username or password!");
            return "login";
        }
    }
}
