package com.boa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage extends Base {
   /* public  BasePage() {
        PageFactory.initElements(DriverContext.driver,this);
    }*/

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance){
        try {
            return (TPage) this;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
