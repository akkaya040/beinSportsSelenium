package com.beinsport.pages;

import com.beinsport.AbstractPage;
import com.beinsport.objectRepo.PaymentPageObj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PaymentPage extends AbstractPage {

    protected WebDriver driver;
    protected String testCase;

    //Abstracter
    public PaymentPage(WebDriver driver, String testCase) {
        super(driver);
        this.driver = driver;
        this.testCase = testCase;
    }

    public PaymentPage controlPopup(String header, String content) throws InterruptedException {
        LogINFO(testCase + ": Popup's All Content will be checked.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentPageObj.popupHeader));

        String head = getTextOfElement(PaymentPageObj.popupHeader);
        String cont = getTextOfElement(PaymentPageObj.popupContent);

        control(header.equals(head)
                , testCase + ": Header informations are validated."
                , testCase + ": Header informations are not identical.");

        control(content.equals(cont)
                , testCase + ": Content informations are validated."
                , testCase + ": Content informations are not identical.");


        return this;
    }


    public PaymentPage closePopup() throws InterruptedException {

        click(PaymentPageObj.btnClose);
        LogINFO(testCase + ": Popup was Closed.");

        return this;
    }

    public PaymentPage markConfirmation() throws InterruptedException {

        click(PaymentPageObj.chkboxConfirmation);
        LogINFO(testCase + ": Confirmation was ticked. ");

        return this;
    }

    public PaymentPage clickPayNow() throws InterruptedException {

        click(PaymentPageObj.btnPayNow);
        LogINFO(testCase + ": Pay-Now button was clicked.");

        return this;
    }

    public PaymentPage printOrderDetails() {
        LogINFO(testCase + ": Listing order details..");
        List<WebElement> orderDetails = findElements(PaymentPageObj.orderDetails);

        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getText().contains("Order reference"))
                LogINFO(testCase + ": " + orderDetails.get(i).getText());
            if (orderDetails.get(i).getText().contains("Total charge"))
                LogINFO(testCase + ": " + orderDetails.get(i).getText());
            if (orderDetails.get(i).getText().contains("Beneficiary"))
                LogINFO(testCase + ": " + orderDetails.get(i).getText());
        }

        return this;
    }

    public PaymentPage validateOrderPrice(String expectedPaymentValue) {

        List<WebElement> orderDetails = findElements(PaymentPageObj.orderDetails);
        String orderPriceFromPage = "";
        for (int i = 0; i < orderDetails.size(); i++) {
            if (orderDetails.get(i).getText().contains("Total charge")) {
                orderPriceFromPage = orderDetails.get(i).getText();
                break;
            }
        }

        control(orderPriceFromPage.contains(expectedPaymentValue)
                , testCase + ": Order Price's which are incoming, are identical."
                , testCase + ": Order Price Validation is not Successful");


        LogINFO(testCase + ": Order Price was Validated.");

        return this;
    }

    public PaymentPage fillCardInformations(ArrayList<String> creditCard) throws InterruptedException {


        sendKeys(PaymentPageObj.fieldCardName, creditCard.get(0).toString());
        sendKeys(PaymentPageObj.fieldCardNumber, creditCard.get(1).toString());
        selectCombobox(PaymentPageObj.fieldCardMonth, creditCard.get(2).toString());
        selectCombobox(PaymentPageObj.fieldCardYear, creditCard.get(3).toString());
        sendKeys(PaymentPageObj.fieldCVC, creditCard.get(4).toString());

        LogINFO(testCase + ": Credit Card Informations were filled");

        return this;
    }

    public PaymentPage clickFinalPaymentButton() throws InterruptedException {

        click(PaymentPageObj.btnPayment);
        LogINFO(testCase + ": Payment button was clicked.");
        return this;
    }


    public PaymentPage controlAlertMessageAndClose(String expectedMessage) throws InterruptedException {
        String alertMessage = getAlertAndAccept();

        control(expectedMessage.equals(alertMessage)
                , testCase + ": Alert Message is Validated."
                , testCase + ": Messages are not equal!");


        return this;
    }
}
