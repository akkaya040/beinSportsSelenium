package com.beinsport.tests;

import com.beinsport.AbstractPage;
import com.beinsport.DriverInit;
import com.beinsport.pages.CreateAccountPage;
import com.beinsport.pages.LandingPage;
import com.beinsport.pages.PaymentPage;
import com.beinsport.pages.SubscribePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class CustomerTransactions {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    @BeforeMethod
    public void Before() {

        DriverInit driverInit = new DriverInit();
        driverInit.setDriver(driver);
        //this.driver = driverInit.getDriver();
        //log.info("Driver is up.");

    }

    @Test(enabled = true, description = "Start Stop Browser")
    public void testCase000() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver.get());
        landingPage
                .navigateToUrl();


    }

    @Test(enabled = true, description = "BeinSports Connect Thailand Subscription Test Case")
    public void testCase001() throws InterruptedException {

        CreateAccountPage createAccountPage = new CreateAccountPage(driver.get());
        LandingPage landingPage = new LandingPage(driver.get());
        PaymentPage paymentPage = new PaymentPage(driver.get());
        SubscribePage subscribePage = new SubscribePage(driver.get());

        int expectedPackageCount = 4;
        String expectedPackageName = "";

        landingPage
                .navigateToUrl()
                .controlSubsciptionButton()
                .clickToSubsciptionButton();

        subscribePage
                .controlPackages(expectedPackageCount)
                .controlIsPackageExist(expectedPackageName);




    }


    @AfterMethod
    public void After() {
        //after method
        driver.get().close();
        driver.get().quit();
    }

}