package com.selenium.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sun.security.krb5.internal.crypto.Des;

import java.net.MalformedURLException;
import java.net.URL;

public class SendEmailTest {
    WebDriver driver;
    @BeforeTest
    public void openChrome() throws MalformedURLException {
        //DesiredCapabilities dc=DesiredCapabilities.chrome();
        //driver=new RemoteWebDriver(new URL("http://172.28.1.81:5555/wd/hub"),dc);
    }
    @DataProvider(name="data4")
    public Object[][] test1()
    {
         return new Object[][]{{"chrome"}};
    }
    @Test(dataProvider = "data4")
    public void sendEmail(String browser) throws MalformedURLException
    {
        DesiredCapabilities dc = null;
        if (browser.equals("firefox"))
        {
            dc=DesiredCapabilities.firefox();
        }
        else if(browser.equals("chrome"))
        {
            dc= DesiredCapabilities.chrome();
        }
        else
        {
            System.out.println("Test");
        }
       driver=new RemoteWebDriver(new URL("http://172.28.1.81:5555/wd/hub"),dc);
       driver.get("https://mail.163.com/");
       LoginTest.loginElement(driver,"17709183385","lixing12345");
       String path="//*[@id=\"dvNavTop\"]/ul/li[2]/span[2]";
       LoginTest.waitElement(driver,3,path);
       driver.findElement(By.xpath(path)).click();
       driver.findElement(By.className("nui-editableAddr-ipt")).sendKeys("17709183385@163.com");
       driver.findElement(By.xpath("//*[@aria-label=\"邮件主题输入框，请输入邮件主题\"]/input")).sendKeys("auto Test");
           //上传文件
       driver.findElement(By.xpath("//*[@title=\"点击添加附件\"]/input")).sendKeys("C:\\Users\\Administrator\\Desktop\\Capture.PNG");
       driver.switchTo().frame(driver.findElement(By.className("APP-editor-iframe")));
       driver.findElement(By.tagName("body")).sendKeys("test test test.......");
       //控制权转换到当前页面
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath(".//*[text()='发送']")).click();
        driver.findElement(By.xpath(".//*[text()='发送']")).click();
        LoginTest.waitElement(driver,3,".//*[text()='发送成功']");
        boolean flag=driver.findElement(By.xpath(".//*[text()='发送成功']")).isDisplayed();
        Assert.assertTrue(flag);
    }
    @AfterMethod
    public void closeChrome()
    {
        driver.quit();
    }
}
