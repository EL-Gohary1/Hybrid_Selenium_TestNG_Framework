package com.tutorialsninja.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driversThreadLocal = new ThreadLocal<>();

    @Parameters({"browser"})
    @BeforeMethod
    public void startDriver(String browserName) {

        WebDriver driver;
        if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        else {
            driver = new ChromeDriver();
        }
        driversThreadLocal.set(driver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().navigate().to("https://tutorialsninja.com/demo/");
    }

    public static WebDriver getDriver() {
        return driversThreadLocal.get();
    }

    @AfterMethod
    public void stopDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driversThreadLocal.remove();
        }
    }
}
