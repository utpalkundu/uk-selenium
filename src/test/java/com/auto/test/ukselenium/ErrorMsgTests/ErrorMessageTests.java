package com.auto.test.ukselenium.ErrorMsgTests;

import com.auto.test.ukselenium.SpringBaseTest;
import com.auto.test.ukselenium.page.LoginPages.LoginPage;
import com.auto.test.ukselenium.taf.utils.ScreenshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class ErrorMessageTests extends SpringBaseTest {

    @Autowired
    private LoginPage hmrcLoginPage;

    @Autowired
    private ScreenshotUtil screenshotUtil;

    @Value("${app.url}")
    private String url;

    @BeforeTest
    public void goTo() throws IOException, InterruptedException {
        this.hmrcLoginPage.goTo(this.url);
        this.hmrcLoginPage.click("Accept analytics cookies");
        this.hmrcLoginPage.isPageLoaded();
        this.screenshotUtil.takeScreenshot();
    }

    @Test
    public void errorMessageTest() {

//        |EMAIL_ADDRESS|PASSWORD|ERROR_MESSAGE|
//        |||Enter an email address;Enter a password|
//        |user@test.com||Enter a password|

        List<Map<String, String>> fieldValues = new ArrayList<>();
        Map<String, String> fieldValue1 = new HashMap<>();
        fieldValue1.put("EMAIL_ADDRESS", "");
        fieldValue1.put("PASSWORD", "");
        fieldValue1.put("ERROR_MESSAGE", "Enter an email address;Enter a password");

        fieldValues.add(fieldValue1);

        Map<String, String> fieldValue2 = new HashMap<>();
        fieldValue2.put("EMAIL_ADDRESS", "user@test.com");
        fieldValue2.put("PASSWORD", "");
        fieldValue2.put("ERROR_MESSAGE", "Enter a password");

        fieldValues.add(fieldValue2);


        fieldValues.forEach((m) -> {
            Map<Consumer<String>, String> map = new LinkedHashMap<>();
            map.put(hmrcLoginPage::enterEmailAddress, m.get("EMAIL_ADDRESS"));
            map.put(hmrcLoginPage::enterPassword, m.get("PASSWORD"));
            map.put(hmrcLoginPage::click, "Sign In");
            this.hmrcLoginPage.checkErrorMessage(map, Arrays.asList(m.get("ERROR_MESSAGE").split(";")));

        });

    }


}
