package com.gsh.springbootquick.system.controller;

import com.gsh.springbootquick.system.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create By GSH on .
 */
@Controller
public class modelAttribute {

//    @ModelAttribute
//    public void getUser(@RequestParam(value="username",required=false) String username, Model model){
//        User user = new User(username,"123456");
//        model.addAttribute("user", user);
//    }
//
//    @RequestMapping("/testModelAttribute")
//    public String testModelAttribute(ModelMap model){
//        System.out.println("user:"+model.get("user"));
//        return "success";
//    }


//==========================================================================================

//    @ModelAttribute
//    public User getUser(@RequestParam(value="username",required=false) String username){
//        User user = new User(username,"123456");
//        return user;
//    }
//
//    @ModelAttribute("user1")
//    public User getUser1(@RequestParam(value="username",required=false) String username,Model model){
//        User user = new User(username,"123456");
//        return user;
//    }

    //==========================================================================================

    @ModelAttribute("user")
    public User getUser(Model model){
        User user = new User("Ben","123456");
        return user;
    }

    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute User user1, Model model){
        System.out.println("User:"+user1);
        System.out.println("model:"+model.toString());
        return "success";
    }

//    @RequestMapping("/testModelAttribute")
//    public String testModelAttribute(@ModelAttribute("username")String username, Model model){
//        System.out.println("username:"+username);
//        System.out.println("model:"+model.toString());
//        return "success";
//    }

    //==========================================================================================

//    @ModelAttribute("user")
//    public User getUser1(Model model){
//        User user = new User("Ben","123456");
//        return user;
//    }
//
//    @RequestMapping("/testModelAttribute2")
//    public String testModelAttribute1(@ModelAttribute("newUser")User user, Model model){
//        System.out.println("User:"+user);
//        System.out.println("model:"+model.toString());
//        return "success";
//    }
//
//    //==========================================================================================
//
//    @RequestMapping("/testModelAttribute")
//    @ModelAttribute("username")
//    public String test1(@RequestParam(required = false) String username, Model model) {
//        System.out.println("User:"+username);
//        System.out.println("model:"+model.toString());
//        return username;
//    }


//    @RequestMapping("/testModelAttribute")
//    @ModelAttribute("username1")
//    public String test1(@RequestParam(required = false) String username, Model model) {
//        System.out.println("User:"+username);
//        System.out.println("model:"+model.toString());
//        return username;
//    }

//    @RequestMapping("/testModelAttribute")
//    @ModelAttribute("user")
//    public User test1(@RequestParam(required = false) User user, Model model) {
//        System.out.println("User:"+user);
//        System.out.println("model:"+model.toString());
//        return user;
//    }
}
