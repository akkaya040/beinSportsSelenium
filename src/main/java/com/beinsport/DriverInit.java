package com.beinsport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverInit {

    public WebDriver driver;

    public void setDriver(ThreadLocal<WebDriver> WbDriver) {

        String driverPath =  System.getProperty("user.dir");
        System.out.println("Project path : " + driverPath);
        System.setProperty("webdriver.chrome.driver", driverPath+"/chromedriver");
        driver = new ChromeDriver();

        // Wait Max 15 sec for each element
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        WbDriver.set(driver);
        //setAbstractDriver(driver);

    }

    public WebDriver getDriver()  {
        return this.driver;
    }



}
