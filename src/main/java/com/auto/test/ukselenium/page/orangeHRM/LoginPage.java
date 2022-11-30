package com.auto.test.ukselenium.page.orangeHRM;

import com.auto.test.ukselenium.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "txtUsername")
    private WebElement username;

    @FindBy(id = "txtPassword")
    private WebElement password;

    @FindBy(name = "Submit")
    private WebElement submit;


    public void doLogin(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.submit.click();
    }
    @Override
    public boolean isPageLoaded() {
        return this.wait.until(driver -> this.username.isDisplayed());
    }
}
