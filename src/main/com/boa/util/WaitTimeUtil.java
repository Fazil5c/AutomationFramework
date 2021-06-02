package com.boa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class WaitTimeUtil {
    final static int TIMEOUT_IN_SECONDS=120;
    final static int POLL_INTERVAL_IN_MILLS=250;
    static int dynamicWaitTime;

    //TODO : Custom waitTime user defined
    static {
        dynamicWaitTime=120;
    }

    public static WebElement waitForElement(SearchContext parent, By by, int waitTime){
        Wait<SearchContext> wait = new FluentWait<SearchContext>(parent)
                .withTimeout(TIMEOUT_IN_SECONDS, SECONDS)
                .pollingEvery(POLL_INTERVAL_IN_MILLS, MICROSECONDS)
                .ignoring(NoSuchElementException.class);
        try {
          return wait.until(context ->context.findElement(by));
        }
        catch (Throwable t){
            return  null;
        }
    }

    public static WebElement waitForElement(SearchContext parent, By by){
        return waitForElement(parent,by,dynamicWaitTime);
    }

    private static WebElement waitForElementToLocated(SearchContext parent,By by){
        return waitForElement(parent,by);
    }

    public static List<WebElement> waitForElements(SearchContext parent, By by, int waitTime){
        Wait<SearchContext> wait = new FluentWait<SearchContext>(parent)
                .withTimeout(TIMEOUT_IN_SECONDS, SECONDS)
                .pollingEvery(POLL_INTERVAL_IN_MILLS, MICROSECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            return wait.until(context ->context.findElements(by));
        }
        catch (Throwable t){
            return  null;
        }
    }

    public static List<WebElement> waitForElements(SearchContext parent, By by){
        return waitForElements(parent,by,dynamicWaitTime);
    }

    private static List<WebElement> waitForElementsToLocated(SearchContext parent,By by){
        return waitForElements(parent,by);
    }

    public static boolean waitForElementToHide(SearchContext parent,By by, int waitTime){
        Wait<SearchContext> wait = new FluentWait<SearchContext>(parent)
                .withTimeout(TIMEOUT_IN_SECONDS, SECONDS)
                .pollingEvery(POLL_INTERVAL_IN_MILLS, MICROSECONDS)
                .ignoring(NoSuchElementException.class);

        try {
            return wait.until(context -> {
                List<WebElement> element = context.findElements(by);
                if (element.size() == 0) {
                    return true;
                }
                return !element.get(0).isDisplayed();
            });
        }
        catch (Throwable t){
            return  false;
        }
    }

    public static boolean waitForElementToHide(SearchContext parent,By by){
        return waitForElementToHide(parent,by,dynamicWaitTime);
    }

    public static WebElement waitForElementClickable(SearchContext parent, By by, int waitTime){
        Wait<SearchContext> wait = new FluentWait<SearchContext>(parent)
                .withTimeout(TIMEOUT_IN_SECONDS, SECONDS)
                .pollingEvery(POLL_INTERVAL_IN_MILLS, MICROSECONDS)
                .ignoring(NoSuchElementException.class);

        try{
            return  wait.until((context -> {
                WebElement element=context.findElement(by);
                if (element==null){
                    return  null;
                }
                return element.isDisplayed() && element.isEnabled() ? element : null;
            }));
        }
        catch (Throwable t){
            return  null;
        }
    }

    public static WebElement waitForElementClickable(SearchContext parent, By by){
        return waitForElementClickable(parent,by,dynamicWaitTime);
    }


}
