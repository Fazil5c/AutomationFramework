package com.boa.base;

import com.boa.util.WaitTimeUtil;
import org.openqa.selenium.*;

public class BaseTestElements {
    private Throwable t;

    private <T> T setException(Throwable t, T returnValue) {
        this.t = t;
        return returnValue;
    }

    protected void clearException() {
        this.t = null;
    }

    public boolean isElementVisible(WebElement element) {
        this.clearException();

        if (element == null)
            return this.setException(new NullPointerException("Element is null"), false);

        try {
            if (!element.isDisplayed())
                return this.setException(new ElementNotVisibleException("Element: " + element + "is not visible to interact"), false);
            return true;
        } catch (StaleElementReferenceException e) {
            throw e;
        } catch (Throwable t) {
            return this.setException(t, false);
        }

    }

    public WebElement findElement(SearchContext parent, By by) {
        WebElement element = WaitTimeUtil.waitForElement(parent, by);

        if (element == null)
            return this.setException(new NoSuchElementException("Unable to find the element " + by + " within the searchContext " + parent), null);

        return element;
    }

    public WebElement findElementClickable(SearchContext parent, By by) {
        WebElement element = WaitTimeUtil.waitForElementClickable(parent, by);

        if (element == null)
            return this.setException(new NoSuchElementException("Unable to find the element " + by + " within the searchContext " + parent), null);

        return element;
    }

    public boolean clickElm(SearchContext parent, By by) {
        WebElement element = this.findElementClickable(parent, by);
        element.click();
        return true;
    }

}
