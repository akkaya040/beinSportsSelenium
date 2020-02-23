package com.beinsport.pages;

import com.beinsport.AbstractPage;
import com.beinsport.objectRepo.SubscribePageObj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubscribePage extends AbstractPage {

    protected WebDriver driver;
    protected String testCase;

    //Abstracter
    public SubscribePage(WebDriver driver, String testCase) {
        super(driver);
        this.driver = driver;
        this.testCase = testCase;

    }

    public SubscribePage controlPackages(int expectedPackageCount) {

        List<WebElement> packages = findElements(SubscribePageObj.packages);

        control(packages.size() == expectedPackageCount
                , "Packages are exist. Count: " + expectedPackageCount
                , "Packages count is not equal to expectedValue!");

        return this;
    }


    public SubscribePage clickToPackage(String expectedPackageName) {

        List<WebElement> packages = findElements(SubscribePageObj.packages);

        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).getText().contains(expectedPackageName)) {
                packages.get(i).findElement(SubscribePageObj.btnSubscribes).click();
                break;
            }

        }

        return this;
    }

    public SubscribePage validatePackagePrice(String expectedPrice) {

        List<WebElement> packages = findElements(SubscribePageObj.packages);
        String price = "";

        for (int i = 0; i < packages.size(); i++) {
            if (packages.get(i).getText().contains(expectedPrice)) {
                price = packages.get(i).findElement(SubscribePageObj.txtPrice).getText();
                break;
            }
        }

        control(price.equals(expectedPrice)
                , "Package price is expectedPrice: " + expectedPrice
                , "Package price couldn't be verified!");

        return this;
    }
}
