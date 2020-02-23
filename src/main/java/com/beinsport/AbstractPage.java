package com.beinsport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait, waitZero, waitLoader;
    public static final int DEFAULT_WAIT = 60;
    public static final int DEFAULT_WAIT_LOADER = 60;
    public static final int DEFAULT_WAIT_LOADERBOX = 90;
    public static final Logger log = (Logger) LogManager.getLogger(AbstractPage.class.getName());

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT);
        this.waitZero = new WebDriverWait(driver, 0);
        this.waitLoader = new WebDriverWait(driver, DEFAULT_WAIT_LOADER);// 90
    }

    public void setAbstractDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getAbstractDriver() {
        return this.driver;
    }

    public void navigateTo(String url) {
        try {
            driver.get(url);
            driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

            log.info("Web application launched");
            LogINFO("Web application launched");
        } catch (Exception e) {

            log.error("Error while getting app url : " + e);
            LogERROR("Error while getting app url : " + e);

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
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            log.error("Error while waiting until element appears : " + e);
            throw new RuntimeException(e);
        }
    }

    public void waitLoaderBox(int time) {

        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

        //if (driver.findElements(By.xpath("//div[starts-with(@class,'loading-main loading')]")).size() != 0) {
        if (driver.findElements(By.cssSelector("[id='loading']")).size() != 0) {
            driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id='loading' and @style='display: none;' and @data-was-processed='true']"));
            //*[@id="loading" and @style='display: none;']
            //*[@id="loading" and @data-was-processed="true"]
            //driver.findElement(By.xpath("//div[@class='loader-box' and @style='display: none;']"));
            //log.info("Waiting For Loader Box!");
            //System.out.println("Waiting For Loader Box!");
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

    protected void sendKeys(By by, String text) throws InterruptedException {
        WebElement element = null;
        String elemText = "";
        try {
            element = findElement(by);
            if (element.isEnabled()) {
                elemText = element.getText();
                element.sendKeys(text);
                LogINFO("Text: '"+text + "' filled to Field: "+elemText );
            }
        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            LogFAIL("Error while filling field : " + e);

            throw new RuntimeException(e);
        }
    }

    protected void sendKeys(WebElement element, String text) throws InterruptedException {
        String elemText = "";

        try {
            if (element.isEnabled()) {
                elemText = element.getText();
                element.sendKeys(text);
                LogINFO("Text: '"+text + "' filled to Field: "+elemText );
            }

        } catch (Exception e) {
            log.error("Error while filling field : " + e);
            LogFAIL("Error while filling field : " + e);

            throw new RuntimeException(e);
        }
    }

    protected String getTextOfElement(By by, int... index) throws InterruptedException {

        String text = null;

        try {

            if (index.length == 0)
                text = driver.findElement(by).getText();
            else
                text = driver.findElements(by).get(index[0]).getText();

        } catch (Exception e) {

            log.error("Error while getting text of element : " + e);
            LogFAIL("Error while getting text of element : " + e);

            throw new RuntimeException(e);
        }
        return text;
    }

    protected void selectCombobox(By by, String value) throws InterruptedException {
        WebElement element = findElement(by);
        String elemText = "";
        try {
            if (element.isEnabled()) {
                    elemText = element.getText();
                Select selectBox = new Select(findElement(by));
                selectBox.selectByValue(value);
            }
            LogPASS("Selected : " + value + " - SelectComboBox : " + elemText);
        } catch (Exception e) {
            log.error("Error while filling field : " + value + " - " + e);
            LogFAIL("Error while filling field : " + value + " - " + e);

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

    public int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public String getRandomMail(){
        String tail = "@gmail.com";
        String head = "kurtulus";
        String rand = String.valueOf(getRandomNumberInRange(100000,999999));

        return head + rand + tail ;
    }

    public String getAlertAndAccept() throws InterruptedException {

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        LogINFO("Chrome Alert Accepted.");
        return alertText;
    }


    public void control(boolean statement, String onTrue, String onFalse) {

        if (statement == true) {
            LogPASS(onTrue);
        } else {
            LogFAIL(onFalse);
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
