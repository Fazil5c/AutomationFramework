package com.boa.pages;

import com.boa.base.BasePage;
import com.boa.base.DriverContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage() {
        super();
    }

    public String getTitle(){
        String homePageTitle = DriverContext.driver.getTitle();
        return homePageTitle;
    }
}
