package com.boa.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "./src/main/com/boa/util/chromedriver_90.exe");
        WebDriver driver=new ChromeDriver();
        By byEmailID = By.id("email");
        driver.get("http://www.leafground.com/pages/Edit.html");
        driver.findElement(byEmailID).sendKeys("Fazil");
        driver.close();
    }
}
