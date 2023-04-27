package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.rmi.Remote;


public class configurations {
    private static WebDriver chromeDriver;
    public static WebDriver getDriver(){
        System.setProperty("webdriver.chrome.driver","C:\\Program Files\\ChromeDriver\\chromedriver.exe");

        ChromeOptions driver_options=new ChromeOptions();
        driver_options.addArguments("--remote-allow-origins=*");
        driver_options.setCapability("browserVersion","112.0.5615.50");

        chromeDriver=new ChromeDriver(driver_options);
        return chromeDriver;
    }
}
