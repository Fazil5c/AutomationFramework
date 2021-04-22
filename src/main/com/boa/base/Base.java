package com.boa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class Base {
    public static BasePage currentPage;

    public <TPage extends BasePage>TPage getInstance(Class<TPage> page){
        Object obj= PageFactory.initElements(DriverContext.driver,page);
        return page.cast(obj);
    }
}
