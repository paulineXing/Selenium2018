package com.selenium.day1;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OpenBrowserTest {
    @Test
    public void OpenFF() throws InterruptedException {
        WebDriver driver=new FirefoxDriver();

    }
    @Test
    public void OpenFF_NoPath() throws InterruptedException {
        System.setProperty("webdriver.firefoxdriver.bin","C:\\Program Files\\Mozilla Firefox");
        WebDriver driver=new FirefoxDriver();
        Thread.sleep(5000);

        driver.close();
    }
    @Test
    public void OpenChrome() throws InterruptedException {
        //System.setProperty("webdriver.chromedriver.bin","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
       // Dimension dimension=new Dimension(500,500);
       // driver.manage().window().setSize(dimension);
        driver.get("https://www.baidu.com");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        String currentUrl=driver.getCurrentUrl();
        String title=driver.getTitle();
        System.out.println(currentUrl+":"+title);
        Assert.assertEquals(currentUrl,"https://www.baidu.com/");
        Thread.sleep(3000);
        //浏览器后退
       // driver.navigate().back();
        //Thread.sleep(3000);
       // driver.navigate().forward();
       // Thread.sleep(3000);

        //完全退出浏览器
        driver.quit();
    }
    @Test
    public void OPenIE()
    {
        System.setProperty("webdriver.ie.driver","F:\\Selenium2018\\drivers\\IEDriverServer.exe");
        WebDriver driver = new InternetExplorerDriver();
    }
    @Test
    public void OpenEdge()
    {
       System.setProperty("webdriver.edge.driver","F:\\Selenium2018\\drivers\\MicrosoftWebDriver.exe");
        WebDriver driver = new EdgeDriver();
    }

}
