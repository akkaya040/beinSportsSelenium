package com.beinsport.pages;

import com.beinsport.AbstractPage;
import com.beinsport.objectRepo.CreateAccountPageObj;
import org.openqa.selenium.WebDriver;
import sun.net.www.content.text.PlainTextInputStream;

public class CreateAccountPage extends AbstractPage {

    protected WebDriver driver;
    protected String testCase;

    //Abstracter
    public CreateAccountPage(WebDriver driver, String testCase) {
        super(driver);
        this.driver = driver;
        this.testCase = testCase;
    }


    public CreateAccountPage fillFormFields(String name,String lastname,String password) throws InterruptedException {

        waitLoaderBox(DEFAULT_WAIT);

        sendKeys(CreateAccountPageObj.fieldName, name);
        sendKeys(CreateAccountPageObj.fieldLastName, lastname);
        sendKeys(CreateAccountPageObj.fieldEmail, getRandomMail());
        sendKeys(CreateAccountPageObj.fieldPassword, password);
        click(CreateAccountPageObj.chkboxMailing);

        LogINFO(testCase + ": Form Fields are filled.");

        return this;
    }

    public CreateAccountPage clickToCreateAccountButton() throws InterruptedException {

        click(CreateAccountPageObj.btnCreateAccount);
        LogINFO(testCase + ": Clicked to Create Account Button.");

        return this;
    }
}
