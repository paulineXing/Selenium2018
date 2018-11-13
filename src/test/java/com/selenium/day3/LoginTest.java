package com.selenium.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {
   private WebDriver driver;
    @BeforeTest
    public void openChrome()
    {
        driver=new ChromeDriver();
        driver.get("https://mail.163.com/");
    }
    @Test
    public void loginSucces()  {

        loginElement(driver,"17709183385","aa");
        waitElement(driver,10,"//*[@id=\"_mail_component_35_35\"]/a");
        String loginout=driver.findElement(By.xpath("//*[@id=\"_mail_component_35_35\"]/a")).getText();
        Assert.assertEquals(loginout,"退出");
    }
    @Test
    public void loginError()
    {
        loginElement(driver,"17709183385","aa");
        waitElement(driver,3,"//*[@id=\"nerror\"]/div[2]");
        String msg=driver.findElement(By.xpath("//*[@id=\"nerror\"]/div[2]")).getText();
        Assert.assertEquals(msg,"帐号或密码错误");
    }
    public  static void loginElement(WebDriver driver,String username,String pwd)
    {
        waitElement(driver,5,"//iframe[contains(@id,'x-URS-iframe')]");
        WebElement frameElement=driver.findElement(By.xpath("//iframe[contains(@id,'x-URS-iframe')]"));
        driver.switchTo().frame(frameElement);
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(pwd);
        driver.findElement(By.id("dologin")).click();
    }
    public  static void waitElement(WebDriver driver,long second,String path)
    {
        WebDriverWait driverWait=new WebDriverWait(driver,second);
        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
    }
    @AfterTest
    public void closeChrome()
    {
        driver.quit();
    }
}
