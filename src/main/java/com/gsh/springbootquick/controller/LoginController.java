package com.gsh.springbootquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Create By GSH on .
 */
@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username) && password.equals("123")){
            session.setAttribute("loginUser", username);
            //成功，防止表单重复提交，可以重定向到主页
            return "redirect:/main.html";
        }else {
            map.put("msg", "用户名密码错误");
            return "login";
        }

    }
}
