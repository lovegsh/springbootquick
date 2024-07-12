package com.gsh.springbootquick.system.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create By GSH on .
 */
@Data
@Component
//@PropertySource(value={"classpath:person.properties"})
@ConfigurationProperties(prefix = "person")
public class Person {

//    @Value("${person.last-name}")
    private String lastName;
//    @Value("#{11*2}")
    private Integer age;
//    @Value("true")
    private boolean bool;
    private Date birth;
    private List<Object> list;
    private Map<String, Object> map;
    private Dog dog;
}
