package com.auto.test.ukselenium.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User {

//  Field Injection
//  ********************************************************************************************************************

    @Autowired
    private Address address;

    @Autowired
    private Salary salary;

//  Constructor Injection
//  ********************************************************************************************************************

//    public User(Address address, Salary salary) {
//        this.address = address;
//        this.salary = salary;
//    }
//  ********************************************************************************************************************

//    Setter method Injection
//  ********************************************************************************************************************
//    @Autowired
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    @Autowired
//    public void setSalary(Salary salary) {
//        this.salary = salary;
//    }
//  ********************************************************************************************************************

    public void printDetails(){
        System.out.println("Address : " + this.address.getStreet());
        System.out.println("Salary : " + this.salary.getAmount());
    }
}
