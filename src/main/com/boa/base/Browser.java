package com.boa.base;

import org.openqa.selenium.WebDriver;

public class Browser {
    private WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public BrowserType Type;

    public void goToURL(String url){
        driver.get(url);
    }

    public void maximize(){
        driver.manage().window().maximize();
    }
}
