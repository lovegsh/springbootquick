package com.gsh.springbootquick.system.controller;

import com.gsh.springbootquick.common.util.JwtUtil;
import com.gsh.springbootquick.system.entity.Result;
import com.gsh.springbootquick.system.entity.User;
import com.gsh.springbootquick.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GSH
 * @create 2023/2/3 14:13
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController{

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('system:user:list')")
    Result<User> getById(@PathVariable Long id) {
        log.info("查询用户信息，id：{}", id);
        return Result.ok(userService.findById(id));
    }

    @PostMapping("/save")
    Result<Void> save(@RequestBody User user) {
        userService.save(user);
        return Result.ok();
    }

    @PutMapping("/update")
    Result<Void> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    Result<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return Result.ok();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    Result<String> login(@RequestBody User user) {
        userService.login(user);
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());
        return Result.ok(token);
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/testJSON")
    public Map<String, String> test2(@RequestHeader("token") String token, String code){
        Map<String, String> map = new HashMap<>();
        map.put("access_token", token);
        map.put("code", code);
        return map;
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<User> findUsers(){
        return userService.findUsers();
    }

    //@TemplateQuery返回部分Entity失败  报错:Column 'password' not found.
//    @GetMapping("/find/{id}")
//    @ResponseBody
//    public UserEntity findUserById(@PathVariable("id") Integer id){
//        return userService.findAllById(id);
//    }
//
//    //jpa自动根据方法名生成
//    @GetMapping("/find2/{id}")
//    @ResponseBody
//    public UserEntity findUserById2(@PathVariable("id") Integer id){
//        return userService.findAllById2(id);
//    }
//
//    //dto全部字段
//    @GetMapping("/find3/{id}")
//    @ResponseBody
//    public UserDTO findUserDtoById(@PathVariable("id") Integer id){
//        return userService.selectDtoById(id);
//    }
//
//    //比dto多一个字段
//    @GetMapping("/find4/{id}")
//    @ResponseBody
//    public UserDTO findUserDtoById2(@PathVariable("id") Integer id){
//        return userService.selectDtoById2(id);
//    }
//
//    //比dto少一个字段
//    @GetMapping("/find5/{id}")
//    @ResponseBody
//    public UserDTO findUserDtoById3(@PathVariable("id") Integer id){
//        return userService.selectDtoById3(id);
//    }
}
