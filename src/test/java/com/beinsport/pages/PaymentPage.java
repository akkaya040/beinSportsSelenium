package com.beinsport.pages;

import com.beinsport.AbstractPage;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends AbstractPage {

    //Abstracter
    public PaymentPage(WebDriver driver) {
        super(driver);
        setAbstractDriver(driver);
    }


}
