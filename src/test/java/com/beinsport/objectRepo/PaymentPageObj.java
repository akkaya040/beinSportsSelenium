package com.beinsport.objectRepo;

import org.openqa.selenium.By;

public class PaymentPageObj {

    public static final By popupHeader = By.cssSelector("div[class='form-header'] [class='title']");
    public static final By popupContent = By.cssSelector("div[class='form '] [class='form-content']");
    public static final By btnClose = By.id("close");
    public static final By chkboxConfirmation = By.cssSelector("[for='checkTerms']");
    public static final By btnPayNow = By.name("pay-now");

    public static final By orderDetails = By.cssSelector("table[class='ncoltable1'] tbody tr");
    public static final By fieldCardName = By.id("Ecom_Payment_Card_Name");
    public static final By fieldCardNumber = By.id("Ecom_Payment_Card_Number");
    public static final By fieldCardMonth = By.id("Ecom_Payment_Card_ExpDate_Month");
    public static final By fieldCardYear = By.id("Ecom_Payment_Card_ExpDate_Year");
    public static final By fieldCVC = By.id("Ecom_Payment_Card_Verification");
    public static final By btnPayment = By.name("payment");


}
