package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class Scraper {
    public static void getData(String courseName,String sortType) {
        String URL="https://www.udemy.com/courses/search/?lang=en&price=price-paid&q=selenium&ratings=4.5&sort=relevance&sort=relevance&src=ukw";
        WebDriver chrome=configurations.getDriver();
        chrome.get(URL);

        System.out.println(chrome.getPageSource());
    }
}
