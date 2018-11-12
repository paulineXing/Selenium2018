package com.selenium.day3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register {
    WebDriver driver;
    @BeforeMethod
    public void openChrome()
    {
        driver=new ChromeDriver();
    }
    @Test
    public void registerUser() throws InterruptedException {//*[@id="x-URS-iframe1541151645688.2314"]
        driver.get("https://mail.163.com/");
        Thread.sleep(3000);
        WebElement frameElement=driver.findElement(By.xpath("//iframe[contains(@id,'x-URS-iframe')]"));
        driver.switchTo().frame(frameElement);
        driver.findElement(By.id("changepage")).click();
        String currentHandle=driver.getWindowHandle();
       for (String handle:driver.getWindowHandles())
        {

            if (currentHandle.equals(handle))
            {
                continue;
            }
            driver.switchTo().window(handle);
        }
     String nameIpt= String.valueOf("EM"+System.currentTimeMillis());
     String mobileIpt=String.valueOf(System.currentTimeMillis()/100);
     driver.findElement(By.id("nameIpt")).sendKeys(nameIpt);
     driver.findElement(By.id("mainPwdIpt")).sendKeys("Bigfoot1!");
     driver.findElement(By.id("mainCfmPwdIpt")).sendKeys("Bigfoot1!");
     driver.findElement(By.id("mainMobileIpt")).sendKeys(mobileIpt);
     driver.findElement(By.id("vcodeIpt")).sendKeys("1252");
     driver.findElement(By.id("mainAcodeIpt")).sendKeys("1252");
     driver.findElement(By.id("mainRegA")).click();
      //显示等待
      WebDriverWait wait=new WebDriverWait(driver,5);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"m_mainAcode\"]/span")));
     String msg=driver.findElement(By.xpath("//*[@id=\"m_mainAcode\"]/span")).getText();
     Assert.assertEquals(msg,"  手机验证码不正确，请重新填写");
    }
    @AfterMethod
    public void closeChrome()
    {
        driver.quit();
    }

}
