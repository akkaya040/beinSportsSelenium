package com.beinsport.pages;

import com.beinsport.AbstractPage;
import com.beinsport.objectRepo.LandingPageObj;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends AbstractPage {

    //Abstracter
    public LandingPage(WebDriver driver) {
        super(driver);
        //setAbstractDriver(driver);
    }



    public LandingPage navigateToUrl() {
        String navigateURL = "https://connect-th.beinsports.com/en";
        log.info("Test is going to URL: " + navigateURL);
        System.out.println("Test is going to URL: " + navigateURL);
        navigateTo(navigateURL);

        return this;
    }

    public LandingPage clickToSubsciptionButton() throws InterruptedException {

        log.info("Clicking to Subscription Button");
        System.out.println("Clicking to Subscription Button");

        WebElement obj = findElement(LandingPageObj.btnSubscribe);
        click(obj);

        System.out.println("Clicked to Subscription Button");
        return this;
    }


    public LandingPage controlSubsciptionButton() {
        System.out.println("Control: is Button Exist");

        control(isElementExist(LandingPageObj.btnSubscribe)
                ,"Subsciption Button is found."
                ,"Subsciption Button is not exist!");

        System.out.println("Success: Button is Exist");
        return this;
    }
}
