package com.boa;

import com.boa.base.*;
import com.boa.pages.HomePage;
import com.boa.pages.LoginPage;
import com.boa.util.ExcelUtil;
import com.boa.util.LogUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;

public class LoginTest extends FrameworkInitialize {
    ExcelUtil excelUtil;
    private static final String EXCEL_FILE_NAME = "Data.xlsx";
    private static final String EXCEL_SHEET_NAME = "Sheet2";

    @BeforeSuite
    public void initialize() {
        LogUtil logUtil = new LogUtil();
        logUtil.createLogFile();
        logUtil.write("Framework Initialize");
        initializeOSAndBrowser(OSType.Ubuntu,BrowserType.Chrome);
        DriverContext.browser.goToURL("https://writingcenter.unc.edu/tips-and-tools/articles/");
    }

    @Test
    public void login() {
        excelUtil = new ExcelUtil();
        LinkedHashMap<String, String> excelData = excelUtil.readExcelSheet(EXCEL_FILE_NAME, EXCEL_SHEET_NAME, "DataRefKey", "Values");
        String username = excelData.get("Username");
        String password = excelData.get("Password");
        LoginPage loginPage=new LoginPage();
        loginPage.login(username,password);
    }

    @AfterTest
    public void closeSession() {
        // driver.close();
    }
}
