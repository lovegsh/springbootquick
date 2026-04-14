package com.gsh.springbootquick.test.reflection.frame;

public class Order {
    private Customer customer;
    private Address address;

    public Order() {}

    @Autowired
    public Order(Customer customer, Address address) {
        this.customer = customer;
        this.address = address;
    }
}
