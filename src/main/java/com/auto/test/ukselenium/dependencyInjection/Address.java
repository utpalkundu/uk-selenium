package com.auto.test.ukselenium.dependencyInjection;

import org.springframework.stereotype.Component;

@Component
public class Address {

    private String street;

    public Address(){
        this.street="8 Blackthorn Close";
    }

    public String getStreet() {
        return street;
    }

}
