package com.selenium.day3;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JSTest {
    WebDriver driver;
    @BeforeTest
    public void openChrome()
    {
        driver=new ChromeDriver();
    }
    @Test
    public void exJs() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        JavascriptExecutor jsEx=(JavascriptExecutor) driver;
        jsEx.executeScript("document.getElementById(\"kw\").setAttribute(\"maxlength\",300)");
        Thread.sleep(3000);
    }
    @AfterMethod
    public void closeChrome()
    {
        driver.quit();
    }
}
