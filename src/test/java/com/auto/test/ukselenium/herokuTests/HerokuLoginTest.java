package com.auto.test.ukselenium.herokuTests;

import com.auto.test.ukselenium.SpringBaseTest;
import com.auto.test.ukselenium.page.heroku.LoginPage;
import com.auto.test.ukselenium.taf.utils.ScreenshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.testng.annotations.*;

import java.io.IOException;

public class HerokuLoginTest extends SpringBaseTest {

    @Lazy
    @Autowired()
    private LoginPage loginPage;

    @Lazy
    @Autowired
    private ScreenshotUtil screenshotUtil;

    @Value("${app.url}")
    private  String url;

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @BeforeMethod
    public void goTo() throws IOException {
        this.loginPage.goTo(this.url);
        this.loginPage.isPageLoaded();
        this.screenshotUtil.takeScreenshot();
    }

    @Test
    public void loginTest1(){
       this.loginPage.setUsername(this.username);
       this.loginPage.setPassword(this.password);
       this.loginPage.click("Login");
    }

    @Test
    public void loginTest2(){
        this.loginPage.setUsername(this.username);
        this.loginPage.setPassword(this.password);
        this.loginPage.click("Login");
    }

    @AfterMethod
    public void teardown(){
        System.out.println(">>>>>>>>>>>. In after method >>>>>>>>>>");
        driver.quit();
    }

}
