package com.beinsport.pages;

import com.beinsport.AbstractPage;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends AbstractPage {

    protected WebDriver driver;
    protected String testCase;

    //Abstracter
    public PaymentPage(WebDriver driver,String testCase) {
        super(driver);
        this.driver = driver;
        this.testCase = testCase;
    }


}
