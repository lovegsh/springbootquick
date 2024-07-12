package com.gsh.springbootquick.system.controller;

import org.gsh.boy.Boy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/start")
public class StarterController {

    @Autowired
    private Boy boy;

    @GetMapping("/say")
    public String say(){
        return boy.say();
    }
}
