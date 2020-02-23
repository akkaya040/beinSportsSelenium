package com.beinsport.pages;

import com.beinsport.AbstractPage;
import com.beinsport.objectRepo.CreateAccountPageObj;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends AbstractPage {

    protected WebDriver driver;
    protected String testCase;

    //Abstracter
    public CreateAccountPage(WebDriver driver,String testCase) {
        super(driver);
        this.driver = driver;
        this.testCase = testCase;
    }


    public CreateAccountPage fillFormFields() throws InterruptedException {

        waitLoaderBox(DEFAULT_WAIT);

        sendKeys(CreateAccountPageObj.fieldName,"Kurtulus");
        sendKeys(CreateAccountPageObj.fieldLastName,"Akkaya");
        sendKeys(CreateAccountPageObj.fieldEmail,getRandomMail());
        sendKeys(CreateAccountPageObj.fieldPassword,"Test1234@");
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
