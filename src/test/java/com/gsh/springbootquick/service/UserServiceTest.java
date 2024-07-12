package com.gsh.springbootquick.service;

import com.gsh.springbootquick.system.entity.User;
import com.gsh.springbootquick.system.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author GSH
 * @create 2023/3/7 9:56
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void getById() {
        User user = userService.findById(2L);
        System.out.println(user);
    }

    @Test
    void save() {
        User user = new User();
        user.setUsername("赵六");
        user.setGender(0);
        user.setPassword("1234567");
        user.setIdCard("110110200001010001");
        user.setEmail("0000@163.com");
        user.setPhone("13333333333");
        user.setBirthday(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        userService.save(user);
    }

    @Test
    void login() {
        User user = new User();
        user.setUsername("李四");
        user.setPassword("1234567");
        userService.login(user);
    }
}

