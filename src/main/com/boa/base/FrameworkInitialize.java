package com.boa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameworkInitialize {

    public void initializeOSAndBrowser(OSType osType,BrowserType browserType) {
        WebDriver driver=null;
        switch (osType) {
            case Ubuntu:
            switch (browserType) {
                case Chrome: {
                    System.setProperty("webdriver.chrome.driver", "/home/fazil/IdeaProjects/AutomationFramework/src/resources/drivers/chromedriver_ubantu_90");
                    driver = new ChromeDriver();
                    break;
                }
                case Firefox: {
                    break;
                }
                case IE: {

                }
                case Opera: {

                }
            }
            break;
            case Windows:
                switch (browserType) {
                    case Chrome: {
                        System.setProperty("webdriver.chrome.driver", ".\\src\\resources\\drivers\\chromedriver_89.exe");
                        driver = new ChromeDriver();
                        break;
                    }
                    case Firefox: {
                        break;
                    }
                    case IE: {

                    }
                    case Opera: {

                    }
                }

        }


        DriverContext.setDriver(driver);
        DriverContext.browser = new Browser(driver);
    }
}
