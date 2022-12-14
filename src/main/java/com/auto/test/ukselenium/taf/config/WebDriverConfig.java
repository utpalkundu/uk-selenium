package com.auto.test.ukselenium.taf.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.time.Duration;

@Lazy
@Configuration
public class WebDriverConfig {
    @Value("${default.timeout:30}")
    private long timeout;

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "safari")
    public WebDriver safariDriver(){
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }

    @Bean
    @Scope("browserscope")
    @ConditionalOnMissingBean
    public WebDriver chromeDriver(){
        WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
        return new ChromeDriver();
    }



    @Bean
    public WebDriverWait webDriverWait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofMillis(this.timeout));
    }


}
