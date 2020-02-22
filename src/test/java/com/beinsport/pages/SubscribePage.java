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

        List<WebElement> packages =  driver.findElements(SubscribePageObj.packages);

        control(packages.size() == expectedPackageCount
                ,"Packages are exist. Count: "+ expectedPackageCount
                ,"Packages count is not equal to expectedValue!");

        return this;
    }


    public SubscribePage controlIsPackageExist(String expectedPackageName) {




        return this;
    }
}
