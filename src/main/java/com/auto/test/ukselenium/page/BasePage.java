package com.auto.test.ukselenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class BasePage {
    @Lazy
    @Autowired
    protected WebDriver driver;

    @Lazy
    @Autowired
    protected WebDriverWait wait;

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isPageLoaded();

    public void goTo(String url){
        this.driver.get(url);
    }

    public void close(){
        this.driver.quit();
    }

    public void jsClick(String str){

        JavascriptExecutor js =
                (JavascriptExecutor) driver;
        WebElement button = driver.findElement(By.xpath("//*[text()='" + str + "']"));
        js.executeScript("arguments[0].click();", button);
    }

    public void click(String str){
        driver.findElement(By.xpath("//*[normalize-space(text())='" + str + "']")).click();
    }

    public void checkErrorMessage(Map <Consumer<String>, String> screenActionsAndParams, List<String> expectedErrorMessages) {

        screenActionsAndParams.forEach(Consumer::accept);

        List <String> actualErrorMessages = driver.findElements(By.cssSelector("[class='nhsuk-list nhsuk-error-summary__list']>li>a"))
                                                                   .stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertTrue(new HashSet<>(actualErrorMessages).containsAll(expectedErrorMessages),"Error messages did not match \n Expected: \n " + expectedErrorMessages +" \n Actual: \n " + actualErrorMessages);
    }


}
