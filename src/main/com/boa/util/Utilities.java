package com.boa.util;

import com.boa.base.Base;
import org.openqa.selenium.By;

public class Utilities {

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


}
