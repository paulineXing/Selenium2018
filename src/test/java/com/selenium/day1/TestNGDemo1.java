package com.selenium.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class TestNGDemo1 {
    WebDriver driver=null;
    @BeforeTest
    public void beforeTc1()
    {

    }
    @BeforeMethod
    public void openBrowser()
    {

         driver=new ChromeDriver();
    }
    @Test
    public void byElementTest() throws InterruptedException {
        driver.get("https://www.baidu.com");
        Thread.sleep(3000);
        //WebElement webElement = driver.findElement(By.id("kw"));
        //WebElement webElement = driver.findElement(By.name("wd"));
        //WebElement webElement = driver.findElement(By.className("s_ipt"));
        //WebElement webElement = driver.findElement(By.linkText("新闻"));
        //WebElement webElement=driver.findElement(By.xpath("//*[@id=\"kw\"]"));
       List<WebElement> webElements=driver.findElements(By.xpath("//*[@id=\"u1\"]/a"));
       for (int i=0;i<webElements.size();i++)
       {
           System.out.println( webElements.get(i).getText());
       }

    }
    @Test
    public void openUrl() throws InterruptedException {
        driver.get("https://www.baidu.com");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        String currentUrl=driver.getCurrentUrl();
        String title=driver.getTitle();
        System.out.println(currentUrl+":"+title);
        Assert.assertEquals(currentUrl,"https://www.baidu.com/");
    }
    @Test
    public void tc2(){
        System.out.println("这是@Test2注解");
    }
    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    @AfterTest
    public void afterTc()
    {
        System.out.println("这是一个AfterTest 注解");
    }
}
