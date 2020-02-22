package com.beinsport.pages;

import com.beinsport.AbstractPage;
import com.beinsport.objectRepo.SubscribePageObj;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubscribePage extends AbstractPage {

    public WebDriver driver;
    //Abstracter
    public SubscribePage(WebDriver driver) {
        super(driver);
        this.driver=driver;

    }

    public SubscribePage controlPackages(int expectedPackageCount){

        List<WebElement> packages =  findElements(SubscribePageObj.packages);

        control(packages.size() == expectedPackageCount
                ,"Packages are exist. Count: "+ expectedPackageCount
                ,"Packages count is not equal to expectedValue!");

        return this;
    }


    public SubscribePage clickToPackage(String expectedPackageName) {

        List<WebElement> packages =  findElements(SubscribePageObj.packages);

        for(int i=0; i<packages.size();i++){
            if(packages.get(i).getText().contains(expectedPackageName)){
                packages.get(i).findElement(SubscribePageObj.btnSubscribes).click();
            }

        }


        return this;
    }
}
