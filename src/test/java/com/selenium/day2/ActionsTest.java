package com.selenium.day2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ActionsTest {
    WebDriver driver=null;
    @BeforeMethod
    public void openChrome()
    {
        driver=new ChromeDriver();
        //全局等待(隐式等待)
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

    }
    @Test
    public void clickTest()
    {
         driver.get("https://www.baidu.com/");
        WebElement newlink = driver.findElement(By.name("tj_trnews"));
        newlink.click();
        String currentUrl=driver.getCurrentUrl();
        Assert.assertEquals(currentUrl,"http://news.baidu.com/");
    }
    @Test
    public void sendkeysTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        WebElement keys = driver.findElement(By.id("kw"));
        keys.sendKeys("selenium");
        WebElement baidubutton = driver.findElement(By.id("su"));
        baidubutton.click();
        Thread.sleep(3000);
        String currentTitle=driver.getTitle();
        Assert.assertEquals(currentTitle,"selenium_百度搜索");
    }
    @Test
    public void clearTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        //WebElement keys = driver.findElement(By.id("kw"));
        //keys.sendKeys("selenium");
       // Thread.sleep(5000);
       // keys.clear();
        //WebElement baidubutton = driver.findElement(By.id("su"));
        //System.out.println(baidubutton.getAttribute("value"));
        //boolean flag=driver.findElement(By.id("su")).isDisplayed();
        //Assert.assertEquals(flag,true);
        Boolean flag=driver.findElement(By.id("su")).isEnabled();
        Assert.assertTrue(flag);
    }
    @Test
    public void shotTest()
    {
        driver.get("https://www.baidu.com/");
        File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       try
       {
           FileUtils.copyFile(file,new File("D:\\test1.png"));
       }
       catch(IOException e){
           e.printStackTrace();
       }

    }
    @Test
    public void alertTest() throws InterruptedException {
      //控制权转交给alert
       Alert alert= driver.switchTo().alert();
       Thread.sleep(3000);
       alert.accept();
    }
    @Test
    public void confirmTest()
    {
        Alert alert=driver.switchTo().alert();
        String msg=alert.getText();//获取警告框 的文本值
        alert.accept();//确定
        alert.dismiss();//取消
    }
    @Test
    public void promptTest() throws InterruptedException {
        Alert alert=driver.switchTo().alert();
        alert.sendKeys("Test");
        alert.accept();
        Thread.sleep(1000);
        alert.accept();
    }
    @Test
    public void iframeTest()
    {
        driver.switchTo().frame("aa");
        driver.switchTo().defaultContent();//控制台转交给原界面

    }
    @Test
    public void selectTest()
    {
        driver.get("url");
        WebElement selectEl = driver.findElement(By.id(""));
        Select select=new Select(selectEl);
        select.selectByIndex(1);//通过索引选择下拉框
        select.selectByValue("");//通过属性value 值来选择下拉框
        select.selectByVisibleText("");//通过文本来选择下拉框
    }
    @Test
    public void windowSelectTest()
    {
        String handle1= driver.getWindowHandle();
        for (String handle:driver.getWindowHandles())
        {
            if (handle.equals(handle1))
            {
                continue;
            }
            else
            {
                driver.switchTo().window(handle);
            }
        }
    }
    @Test
    public void waitMethodTest()
    {
        //显示等待
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("")));

    }

    @Test
    public void rightClickTest()
    {
        driver.get("https://www.baidu.com/");
        WebElement buttonBaidu=driver.findElement(By.id("su"));
        Actions action=new Actions(driver);
        action.contextClick(buttonBaidu).perform();//鼠标右键
        action.doubleClick(buttonBaidu).perform();//鼠标双击
    }
    @Test
    public void MoveToTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        WebElement product=driver.findElement(By.name("tj_briicon"));
        Actions action=new Actions(driver);
        action.moveToElement(product).perform();
        Thread.sleep(3000);
        WebElement linkNuo=driver.findElement(By.name("tj_nuomi"));
        Assert.assertEquals(linkNuo.getText(),"糯米");
        //action.dragAndDropBy(linkNuo,500,500).perform();//执行拖拽到x,y的位置

    }
    //从a拖拽到b
    @Test
    public void dropTest()
    {
        driver.get("https://www.baidu.com/");
        WebElement el1=driver.findElement(By.name("a"));
        WebElement el2=driver.findElement(By.name("b"));
        Actions actions=new Actions(driver);
        actions.clickAndHold(el1)
                .moveToElement(el2)
                .release(el1)
                .perform();
    }
    //下拉框多选
    @Test
    public void moreSelectTest()
    {
        driver.get("https://www.baidu.com/");
        WebElement el1=driver.findElement(By.name("a"));
        List<WebElement> listElements=driver.findElements(By.xpath(""));
        Actions actions=new Actions(driver);
        actions.keyDown(Keys.CONTROL)
                .click(listElements.get(0))
                .click(listElements.get(1))
                .keyUp(Keys.CONTROL)
                .perform();
    }
    @Test
    public void saveHtml() throws AWTException, InterruptedException {
        driver.get("https://www.baidu.com/s");
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_S);
        int keyS=(int)new Character('S');
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ENTER);

    }
    @Test
    public void updateFileTest()
    {
        driver.get("https://www.baidu.com/s");
        driver.findElement(By.name("a")).sendKeys("address");
    }
    @AfterMethod
    public void closeChrome() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
