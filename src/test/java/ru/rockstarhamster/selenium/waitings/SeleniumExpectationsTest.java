package ru.rockstarhamster.selenium.waitings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class SeleniumExpectationsTest {

    private static final String FROM_KRD_TO_MSK_TICKET_SEARCH_URL = "https://www.aviasales.ru/search/KRR3009MOW15101?request_source=search_form&payment_method=all";
    private static final String SUBSCRIPTION_BUTTON_CLASSNAME = "direction-subscriptions__btn-subs";
    private static final String PREDICTION_FIRST_COLUMN_CLASSNAME = "prediction-header__left-col";

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUpWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/alex/IdeaProjects/selenium-watings/src/test/resources/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
    }

    @Test
    public void subscription_button_appears() {
        webDriver.get(FROM_KRD_TO_MSK_TICKET_SEARCH_URL);
        assertDoesNotThrow(() -> webDriver.findElement(By.className(SUBSCRIPTION_BUTTON_CLASSNAME)));
    }

    @Test
    public void prediction_bar_appears() {
        webDriver.get(FROM_KRD_TO_MSK_TICKET_SEARCH_URL);
        final var wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        assertDoesNotThrow(() -> wait.until(ExpectedConditions.presenceOfElementLocated(By.className(PREDICTION_FIRST_COLUMN_CLASSNAME))));
    }

    @AfterAll
    public static void finalizeWebDriver() {
        webDriver.quit();
    }
}
