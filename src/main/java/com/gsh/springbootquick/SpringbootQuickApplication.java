package com.gsh.springbootquick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringbootQuickApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootQuickApplication.class, args);
    }

}
