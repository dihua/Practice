package com.wu.model;


import java.util.List;

/**
 * @author dihua.wu
 * @description
 * @create 2020/7/21
 */
public class User {

    private String name;
    private int age;
    private List<Order> orders;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    @Override
    public String toString() {
        return "User [name=" + name + ", age=" + age + ", orders=" + orders + "]";
    }
}
