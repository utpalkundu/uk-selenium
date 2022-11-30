package com.auto.test.ukselenium.page.google;

import com.auto.test.ukselenium.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResult extends BasePage {

    @FindBy(css = "div.g")
    private List<WebElement> results;

    public int getCount(){
        return this.results.size();
    }

    @Override
    public boolean isPageLoaded() {
        return this.wait.until((d) -> !this.results.isEmpty());
    }

}
