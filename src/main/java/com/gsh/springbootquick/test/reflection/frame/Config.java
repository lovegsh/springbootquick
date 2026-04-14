package com.gsh.springbootquick.test.reflection.frame;

import com.gsh.springbootquick.test.reflection.Message;

public class Config {
    @Bean
    public Customer customer() {
        return new Customer("pony", "pony@qq.com");
    }

    @Bean
    public Address address() {
        return new Address("center street", "114514" );
    }

    public Message message() {
        return new Message("Hi there!");
    }
}
