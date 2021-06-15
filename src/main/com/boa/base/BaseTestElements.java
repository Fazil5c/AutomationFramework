package com.boa.base;

import com.boa.util.DataUtil;
import com.boa.util.HelperUtil;
import com.boa.util.WaitTimeUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import javax.swing.text.Utilities;
import javax.xml.crypto.Data;

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

    public WebElement reIdentifyElement(WebElement webElement) {
        RemoteWebElement remoteWebElement = RemoteWebElement.class.cast(webElement);
        SearchContext parent = remoteWebElement.getWrappedDriver();

        String[] definitions = webElement.toString().split("->");

        for (int index = 1; index < definitions.length; index++) {
            String localString = definitions[index].trim();
            localString = localString.substring(0, localString.length() - (definitions.length - index));
            String[] isArray = localString.split(":", 2);
            By by = HelperUtil.by(isArray[0], isArray[1]);

            webElement= WaitTimeUtil.waitForElement(parent, by, 0);

            if (webElement == null) {
                break;
            }
            parent = webElement;
        }
        return webElement;
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

    public void scrollToView(WebElement element) {
        JavascriptExecutor js = JavascriptExecutor.class.cast(DriverContext.driver);

        try {
            js.executeScript("arguments[0].scrollIntoView()", element);
        } catch (Throwable t) {
            // TODO: need to add respective exception
        }
    }

    public void scrollToView(SearchContext parent, By by) {
        WebElement element = WaitTimeUtil.waitForElementClickable(parent, by);
        if (!DataUtil.isNull(element)) {
            this.scrollToView(element);
        }
    }

    public void scrollToCenter(WebElement element){
        JavascriptExecutor js = JavascriptExecutor.class.cast(DriverContext.driver);
        double yPos=0;
        int desireYPos=360;

        try{
            yPos = DataUtil.getDouble(js.executeScript("return arguments[0].getBoundingClientRect().top", element));
            js.executeScript("window.scrollBy(0,arguments[0]-arguments[1])",yPos,desireYPos);
        }
        catch (Throwable t){
            //TODO
            System.out.println(t.getMessage());
        }

    }
    
    public void scrollToCenter(SearchContext parent,By by){
        WebElement element = WaitTimeUtil.waitForElementClickable(parent, by);
        if(!DataUtil.isNull(element))
         this.scrollToCenter(element);
    }

    public boolean clickElm(WebElement element) {
        try {
            if (!this.isElementClickable(element))
                return false;

            if (HelperUtil.isRecursiveClass()) this.scrollToView(element);

            element.click();

            return true;

        } catch (StaleElementReferenceException e) {
            if (HelperUtil.isRecursive()) return this.setException(e, false);

            element = this.reIdentifyElement(element);

            if (element==null) return setException(e,false);

            return this.clickElm(element);
        }
        catch (ElementClickInterceptedException e){
            if(!HelperUtil.isRecursive()){
                //TODO: Handle if we have overlay in our application

                return this.clickElm(element);
            }
            else if(!HelperUtil.isRecursive(2)){
                //TODO: Jse click
            }
            else{
                return setException(e,false);
            }
        }
        catch (Throwable t){
            return setException(t,false);
        }
        return false;
    }

    public boolean clickElm(SearchContext parent, By by) {

            WebElement element = this.findElementClickable(parent, by);

            if (DataUtil.isNull(element)) return false;

            boolean success = this.clickElm(element);

            if (!success)
                if (HelperUtil.isCausedBy(this.t, StaleElementReferenceException.class))
                    if (!HelperUtil.isRecursive())
                        return this.clickElm(element);

            return success;

        }


    }
