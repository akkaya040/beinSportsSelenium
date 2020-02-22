package com.beinsport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait, waitZero, waitLoader;
    public static final int DEFAULT_WAIT = 60;
    public static final int DEFAULT_WAIT_LOADER = 60;
    public static final int DEFAULT_WAIT_LOADERBOX = 90;
    public static final Logger log = (Logger) LogManager.getLogger(AbstractPage.class.getName());

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
        this.waitZero = new WebDriverWait(driver, 0);
        this.waitLoader = new WebDriverWait(driver, DEFAULT_WAIT_LOADER);// 90
    }

    public void setAbstractDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getAbstractDriver(){
        return this.driver;
    }


    public void navigateTo(String url) {
        try {
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
            //Wait(100);
            log.info("Web application launched");

        } catch (Exception e) {
            log.error("Error while getting app url : " + e);

            throw new RuntimeException(e);
        }
    }

    protected WebElement findElement(By by, int... index) throws InterruptedException {

        WebElement element = null;
        untilElementAppear(by);
        try {
            if (index.length == 0)
                element = driver.findElement(by);
            else
                element = driver.findElements(by).get(index[0]);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);arguments[0].focus();", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));

        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);

            throw new RuntimeException(e);
        }
        return element;
    }

    protected List<WebElement> findElements(By by) {
        List<WebElement> webElements = null;
        untilElementAppear(by);
        try {
            webElements = driver.findElements(by);
        } catch (Exception e) {
            log.error("Error while listing webelements by selector : " + e);
            LogFAIL("Error while listing webelements selector : " + e);
            log.info("Error while listing webelements by selector : " + e);

            throw new RuntimeException(e);
        }
        return webElements;
    }


    protected void untilElementAppear(By by) {

        try {
            waitLoaderBox(DEFAULT_WAIT_LOADERBOX);// , 40
            Thread.sleep(1000);
            // driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            // wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            log.error("Error while waiting until element appears : " + e);
            throw new RuntimeException(e);
        }
    }

    public void waitLoaderBox(int time) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (driver.findElements(By.xpath("//div[starts-with(@id,'loading')]")).size() != 0) {
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//div[@id='loading' and @style='display: none;']"));
            //driver.findElement(By.xpath("//div[@class='loader-box' and @style='display: none;']"));
            log.info("Waiting For Loader Box!");
            System.out.println("Waiting For Loader Box!");
        }
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);
    }

    protected void click(By by, int... index) throws InterruptedException {

        WebElement element;
        try {
            element = findElement(by, index);
            element.click();

        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);
            log.info("Error while clicking webelement : " + e);

            throw new RuntimeException(e);
        }
    }

    protected void click(WebElement element) throws InterruptedException {

        try {
            element.click();
        } catch (Exception e) {
            log.error("Error while clicking webelement : " + e);
            log.info("Error while clicking webelement : " + e);

            throw new RuntimeException(e);
        }
    }

    protected boolean isElementExist(By by) {
        return isElementExist(by, 15);
    }

    protected boolean isElementExist(By by, int timeSeconds) {

        driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);
        boolean isExist = driver.findElements(by).size() > 0;
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT, TimeUnit.SECONDS);

        return isExist;
    }


    public void control(boolean statement, String onTrue, String onFalse) {

        if (statement == true) {
            System.out.println(onTrue);
            LogPASS(onTrue);
        } else {
            LogFAIL(onFalse);
            System.out.println(onFalse);
            Assert.assertTrue(false);
        }
    }

    public void LogPASS(String massege) {
        log.info(massege);
        System.out.println(massege);
    }

    public void LogFAIL(String massege) {
        log.error(massege);
        System.out.println(massege);
    }

    public void LogERROR(String massege) {
         log.error(massege);
         System.out.println(massege);
    }

    public void LogINFO(String massege) {
        log.info(massege);
        System.out.println(massege);
    }


}
