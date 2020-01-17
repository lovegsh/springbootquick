package com.gsh.springbootquick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * Create By GSH .
 */
@Controller
public class HelloController {

//    @RequestMapping({"/","/index"})
//    public String login(){
//        return "login";   //
//    }

    @ResponseBody
    @RequestMapping("/hello")
    public String sayhello(){
        return "hello";
    }

    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("Hello","<h2>World</h2>");
        map.put("users", Arrays.asList("张三","李四","王五"));
        return "success";     //thymeleaf   classpath:/templates/success
    }
}
