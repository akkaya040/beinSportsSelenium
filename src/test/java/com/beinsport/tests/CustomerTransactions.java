package com.beinsport.tests;

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
        String testCase = new Object() {}.getClass().getEnclosingMethod().getName();

        LandingPage landingPage = new LandingPage(driver.get(),testCase);
        landingPage
                .navigateToUrl();


    }

    @Test(enabled = true, description = "BeinSports Connect Thailand Subscription Test Case")
    public void testCase001() throws InterruptedException {
        String testCase = new Object() {}.getClass().getEnclosingMethod().getName();

        CreateAccountPage createAccountPage = new CreateAccountPage(driver.get(),testCase);
        LandingPage landingPage = new LandingPage(driver.get(),testCase);
        PaymentPage paymentPage = new PaymentPage(driver.get(),testCase);
        SubscribePage subscribePage = new SubscribePage(driver.get(),testCase);

        int expectedPackageCount = 4;
        String expectedPackageName = "Monthly Pass with One Week Free Trial";
        String expectedPrice = "฿99";


        landingPage
                    .navigateToUrl()
                    .controlSubsciptionButton()
                    .clickToSubsciptionButton();

        subscribePage
                    .controlPackages(expectedPackageCount)
                    .validatePackagePrice(expectedPrice)
                    .clickToPackage(expectedPackageName);

        createAccountPage
                    .fillFormFields()
                    .clickToCreateAccountButton();


    }


    @AfterMethod
    public void After() {
        //after method
        //driver.get().close();
        //driver.get().quit();
    }

}
