package com.boa.base;

import com.boa.util.DataUtil;
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

    public boolean isElementClickable(WebElement element) {
        if (!this.isElementVisible(element))
            return false;

        if (!element.isEnabled())
            return this.setException(new ElementNotInteractableException("Element: " + element + "is not enabled"), false);

        return true;
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

    public void scrollToView(WebElement element){
        JavascriptExecutor js= JavascriptExecutor.class.cast(DriverContext.driver);

        try {
            js.executeScript("arguments[0].scrollIntoView()",element);
        }
        catch (Throwable t){
           // TODO: need to add respective exception
        }
    }

    public void scrollToView(SearchContext parent,By by){
        WebElement element = WaitTimeUtil.waitForElementClickable(parent, by);
        if(DataUtil.isNull(element)){
            this.scrollToView(element);
        }
    }

   /* public boolean clickElm(WebElement element){
        if(!this.isElementClickable(element))
            return false;


    }*/


}
