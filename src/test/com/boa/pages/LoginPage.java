package com.boa.pages;

import com.boa.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "USERNAME")
    WebElement userNameTxtBox;
    @FindBy(name = "PASSWORD")
    WebElement passwordTxtBox;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement loginBtn;

    public LoginPage() {

    }

    public HomePage login(String userName, String password) {
        userNameTxtBox.sendKeys(userName);
        passwordTxtBox.sendKeys(password);
        loginBtn.click();
        return getInstance(HomePage.class);
    }
}
