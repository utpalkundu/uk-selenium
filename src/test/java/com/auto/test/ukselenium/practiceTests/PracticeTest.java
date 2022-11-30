package com.auto.test.ukselenium.practiceTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.auto.test.ukselenium.dependencyInjection.User;

@SpringBootTest
public class PracticeTest{

    @Autowired
    private User  user;

    @Test
    public void loginTest(){
       this.user.printDetails();
    }



}
