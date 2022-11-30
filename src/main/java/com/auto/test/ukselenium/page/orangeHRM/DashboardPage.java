package com.auto.test.ukselenium.page.orangeHRM;

import com.auto.test.ukselenium.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

@Component
public class DashboardPage extends BasePage {

    @FindBy(id = "welcome")
    private WebElement welcomeMenu;


    @Override
    public boolean isPageLoaded() {
        return wait.until(driver -> this.welcomeMenu.isDisplayed());
    }
}
