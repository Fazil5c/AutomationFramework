package com.boa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameworkInitialize {

    public void initializeBrowser(BrowserType browserType) {
        WebDriver driver=null;

        switch (browserType) {
            case Chrome: {
                System.setProperty("webdriver.chrome.driver", ".\\src\\resources\\drivers\\chromedriver_90.exe");
                driver = new ChromeDriver();
                break;
            }
            case Firefox: {
                break;
            }
            case IE:{

            }
            case Opera:{

            }
        }

        DriverContext.setDriver(driver);
        DriverContext.browser = new Browser(driver);
    }
}
