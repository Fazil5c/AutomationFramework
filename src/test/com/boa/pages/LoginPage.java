package com.boa.pages;

import com.boa.base.BasePage;
import com.boa.base.BaseTestElements;
import com.boa.base.DriverContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    By userNameTxtBox = By.name("USERNAME");
    By passwordTxtBox = By.name("PASSWORD");
    By loginBtn = By.xpath("//input[@value='Login']");
    By gitUrl =By.xpath("//a[normalize-space(text())='Make a Gift']");

    public LoginPage() {

    }

    public HomePage login(String userName, String password) {
       // super.clickElm(DriverContext.driver,loginBtn);
        scrollToCenter(DriverContext.driver,gitUrl);
        clickElm(DriverContext.driver,gitUrl);
        return new HomePage();
    }

}
