package com.barclaycard.collections.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.barclaycard.collections.utils.Configuration.APP_URL;

public class WebBrowser {

    private static WebDriver driver = null;
    private static WebDriverWait wait;

    public static WebDriver start() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);

        //capabilities.setCapability("version", browserVersion);
        capabilities.setCapability("platform", Platform.LINUX);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        wait = new WebDriverWait(driver, 5);

        return driver;
    }

    public static void closeBrowser() {
        driver.close();
    }

    public static void navigateTo(final String url) {
        driver.manage().window().maximize();

        driver.get(APP_URL + url);
    }

    public static WebElement findElement(final By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


}

