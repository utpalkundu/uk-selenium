package com.auto.test.ukselenium.orangeTests;

import com.auto.test.ukselenium.SpringBaseTest;
import com.auto.test.ukselenium.page.orangeHRM.DashboardPage;
import com.auto.test.ukselenium.page.orangeHRM.LoginPage;
import com.auto.test.ukselenium.taf.utils.ScreenshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class OrangeLoginTest extends SpringBaseTest {

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private DashboardPage dashboardPage;

    @Autowired
    private ScreenshotUtil screenshotUtil;

    @Value("${app.url}")
    private  String url;

    @Value("${app.username}")
    private String username;

    @Value("${app.password}")
    private String password;

    @BeforeTest
    public void goTo() throws IOException {
        this.loginPage.goTo(this.url);
        this.loginPage.isPageLoaded();
        this.screenshotUtil.takeScreenshot();
    }
    @Test
    public void loginTest(){
        this.loginPage.doLogin(this.username, this.password);
        this.dashboardPage.isPageLoaded();
    }



}
