package com.boa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.lang.reflect.InvocationTargetException;

public class HelperUtil {

    public static By by(String locateBy, String locator) {
        locator.trim();

        switch (locateBy.toLowerCase().trim().replace(" ", "")) {
            case "id":
                return By.id(locator);
            case "name":
                return By.name(locator);
            case "class":
            case "classname":
                return By.className(locator);
            case "xpath":
                return By.xpath(locator);
            case "linktext":
            case "link":
            case "text":
                return By.linkText(locator);
            case "tag":
            case "tagname":
                return By.tagName(locator);
            case "cssselector":
            case "css":
                return By.cssSelector(locator);
            case "partiallinktext":
            case "partiallink":
            case "partialtext":
                return By.partialLinkText(locator);
            default:
                return null;
        }
    }
    public static WebElement reIdentifyElement(WebElement webElement){
        RemoteWebElement remoteWebElement = RemoteWebElement.class.cast(webElement);
        SearchContext parent = remoteWebElement.getWrappedDriver();

        String[] definitions = webElement.toString().split("->");

        for (int index = 0; index < definitions.length; index++) {
            String localString = definitions[index].trim();
            localString = localString.substring(0, localString.length() - (definitions.length - index));
            String[] isArray = localString.split(":", 2);
            By by = HelperUtil.by(isArray[0], isArray[1]);

            WaitTimeUtil.waitForElement(parent, by, 0);

            if (webElement == null) {
                break;
            }
            parent = webElement;
        }
        return webElement;
    }

    public static boolean isRecursive(int maxAllowed) {
        StackTraceElement[] traces;
        try {
            traces = Thread.currentThread().getStackTrace();
        } catch (SecurityException e) {
            return false;
        }

        StackTraceElement baseTrace = traces[1];

        for (int index = 2; index < traces.length; index++) {
            StackTraceElement currTrace = traces[index];

            if (baseTrace.getClassName().equals(HelperUtil.class.getName())) {
                baseTrace = traces[index];
                continue;
            }

            boolean same = currTrace.getClassName().equals(baseTrace.getClassName()) && currTrace.getMethodName().equals(baseTrace.getMethodName());

            if (!same)
                return false;

            if (--maxAllowed == -1)
                return true;
        }
        return false;
    }

    public static boolean isRecursive(){
        return HelperUtil.isRecursive(1);
    }

    public static boolean isRecursiveClass(){
        StackTraceElement[] traces;
        try {
            traces = Thread.currentThread().getStackTrace();
        } catch (SecurityException e) {
            return false;
        }

        String prevClass=HelperUtil.class.getName();
        String prevMethod="";

        for(StackTraceElement trace:traces){
           if(prevClass.equals(HelperUtil.class.getName())){
              prevClass= trace.getClassName();
              prevMethod= trace.getMethodName();
           }
           else if(prevClass.equals(trace.getClassName())){
             if(!prevMethod.equals(trace.getMethodName()))
                 return true;
           }
           else{
               return false;
           }
        }
        return false;
    }

    public static boolean isCausedBy(Throwable source, Class<? extends Throwable> expected){
        if(source.getClass().equals(expected)) return true;

        Throwable cause = source.getClass().equals(InvocationTargetException.class) ? InvocationTargetException.class.cast(source).getTargetException() : source.getCause();

        if(cause==null) return  false;

        if(cause.equals(source)) return  false;

        return HelperUtil.isCausedBy(cause,expected);
    }
}
