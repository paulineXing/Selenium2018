package com.selenium.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.annotations.Test;

public class PhantomJSTest {
    @Test
    public void pjsTest() throws InterruptedException {
        System.setProperty("phantomjs.binary.path","F:\\Selenium2018\\drivers1\\phantomjs.exe");
        WebDriver driver=new PhantomJSDriver();
        driver.get("https://www.baidu.com");
        driver.findElement(By.id("kw")).sendKeys("test");
        Thread.sleep(3000);
        String currentTitle=driver.getTitle();
        System.out.println(currentTitle);
        driver.quit();
    }
}
