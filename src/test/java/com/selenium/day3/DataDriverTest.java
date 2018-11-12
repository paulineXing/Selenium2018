package com.selenium.day3;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriverTest
{
    @DataProvider(name="UserInfo")
    public Object[][] userdata()
    {
        return new Object[][]{{"user1","1230"},{"user2","1264"}};
    }
    @Test(dataProvider="UserInfo")
    public void testuser(String username,String pwd)
    {
        System.out.println(username+":"+pwd);
    }
}