package com.auto.test.ukselenium.ErrorMsgTests;

import com.auto.test.ukselenium.SpringBaseTest;
import com.auto.test.ukselenium.page.healthPages.LoginPage;
import com.auto.test.ukselenium.taf.utils.ScreenshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class ErrorMessageTests extends SpringBaseTest {

    @Lazy
    @Autowired
    private LoginPage hmrcHealthLoginPage;

    @Autowired
    private ScreenshotUtil screenshotUtil;

    @Value("${app.url}")
    private String url;

    @BeforeTest
    public void goTo() throws IOException, InterruptedException {
        this.hmrcHealthLoginPage.goTo(this.url);
        this.hmrcHealthLoginPage.click("Accept analytics cookies");
        this.hmrcHealthLoginPage.isPageLoaded();
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
            map.put(hmrcHealthLoginPage::enterEmailAddress, m.get("EMAIL_ADDRESS"));
            map.put(hmrcHealthLoginPage::enterPassword, m.get("PASSWORD"));
            map.put(hmrcHealthLoginPage::click, "Sign In");
            this.hmrcHealthLoginPage.checkErrorMessage(map, Arrays.asList(m.get("ERROR_MESSAGE").split(";")));

        });

    }


}
