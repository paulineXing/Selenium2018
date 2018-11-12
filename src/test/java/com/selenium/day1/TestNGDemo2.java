package com.selenium.day1;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGDemo2 {
    //校验a==a
    @Test
    public void assertEqualTest()
    {
        String name="test1";
        String name1="test2";

        Assert.assertEquals(name,name1,"a不等于b");
        System.out.println("-----------");
        Assert.assertEquals(name,name1);
    }
}
