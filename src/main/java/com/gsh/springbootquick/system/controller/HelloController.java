package com.gsh.springbootquick.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/websocket")
    public String websocket(){
        return "websocketTest";
    }

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

    @ResponseBody
    @RequestMapping("/chinese")
    public String chinese(String str){
        System.out.println("中文str == "+str);
        return "返回:"+str;
    }

    @ResponseBody
    @RequestMapping("/chinese2")
    public String chinese2(@RequestBody String str){
        System.out.println("2中文str == "+str);
        return "2返回:"+str;
    }
}
