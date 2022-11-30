package com.auto.test.ukselenium;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

@SpringBootTest
public class SpringBaseTest extends AbstractTestNGSpringContextTests {
    @Lazy
    @Autowired
    protected WebDriver driver;

    @BeforeSuite
    @Override
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    @AfterTest
    public void teardown(){
        System.out.println(">>>>>>>>>>>. In after test >>>>>>>>>>");
        driver.quit();
    }
}
