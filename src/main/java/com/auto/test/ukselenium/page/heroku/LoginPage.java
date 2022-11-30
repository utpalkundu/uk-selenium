package com.auto.test.ukselenium.page.heroku;

import com.auto.test.ukselenium.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component("herokuLoginPage")
public class LoginPage extends BasePage {

    @FindBy(how =How.TAG_NAME, using = "h2")
    private WebElement pageTitle;

    @FindBy(how = How.ID, using = "username")
    private WebElement username;

    @FindBy(how = How.ID, using = "password")
    private WebElement password;

    @FindBy(how = How.CSS, using = ".radius")
    private WebElement loginBtn;

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username.sendKeys(username);
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    @Override
    public boolean isPageLoaded() {
        return this.wait.until(driver -> this.pageTitle.isDisplayed());
    }
}
