package com.boa;

import com.boa.base.*;
import com.boa.pages.HomePage;
import com.boa.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest extends FrameworkInitialize {

    @BeforeSuite
    public void initialize() {
        initializeBrowser(BrowserType.Chrome);
        DriverContext.browser.goToURL("http://leaftaps.com/crmsfa/control/main");
    }

    @Test
    public void login() {
        currentPage = getInstance(LoginPage.class);
        currentPage=currentPage.As(LoginPage.class).login("Demosalesmanager", "crmsfa");
        System.out.println(currentPage.As(HomePage.class).getTitle());
    }


    @AfterTest
    public void closeSession() {
       // driver.close();
    }
}
