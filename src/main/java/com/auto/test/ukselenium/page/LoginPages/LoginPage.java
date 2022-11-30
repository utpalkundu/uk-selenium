package com.auto.test.ukselenium.page.LoginPages;

import com.auto.test.ukselenium.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "submit-button")
    private WebElement signIn;


    public void doLogin(String username, String password){
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.signIn.click();
    }

    public void enterEmailAddress(String emailAddress){
        this.username.sendKeys(emailAddress);
        System.out.println("Entered Email == " + emailAddress);
    }

    public void enterPassword(String password){
        this.password.sendKeys(password);
        System.out.println("Entered Password == " + password);

    }

    @Override
    public boolean isPageLoaded() {
        return this.wait.until(driver -> this.username.isDisplayed());
    }
}
