package ru.rockstarhamster.selenium.waitings;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumExpectationsTest {

    private static final Duration ACCEPT_COOKIE_WAIT_TIMEOUT = Duration.ofSeconds(5);
    private static final String ACCEPT_COOKIE_BUTTON_ID = "onetrust-accept-btn-handler";
    private static final String HM_HOME_PAGE_URL = "https://www2.hm.com/ru_ru/index.html";
    private static final String HM_COOKIE_DOMAIN_NAME = ".hm.com";

    private static final String DOGMA_PAGE_URL = "https://1dogma-krasnodar.ru/";
    private static final Duration ISSUE_POPUP_TIMEOUT = Duration.ofSeconds(60);
    private static final String POP_UP_ISSUE_MODEL_CLASS = "i-lh-main";

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUpWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/alex/IdeaProjects/selenium-watings/src/test/resources/chromedriver");
        webDriver = new ChromeDriver();
    }

    @Test
    public void accept_hm_cookie() {
        webDriver.get(HM_HOME_PAGE_URL);
        final var driverWait = new WebDriverWait(webDriver, ACCEPT_COOKIE_WAIT_TIMEOUT);
        driverWait.until(ExpectedConditions.elementToBeClickable(By.id(ACCEPT_COOKIE_BUTTON_ID)));
        webDriver.findElement(By.id(ACCEPT_COOKIE_BUTTON_ID)).click();
        final var domain = webDriver.manage().getCookies().stream()
                .map(Cookie::getDomain)
                .findFirst();
        assertTrue(domain.isPresent());
        assertEquals(HM_COOKIE_DOMAIN_NAME, domain.get());
    }

    @Test
    public void check_that_user_gets_issue_popup() {
        webDriver.get(DOGMA_PAGE_URL);
        final var driverWait = new WebDriverWait(webDriver, ISSUE_POPUP_TIMEOUT);
        final var webElement = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.className(POP_UP_ISSUE_MODEL_CLASS))
        );
        assertNotNull(webElement);
    }

    @AfterAll
    public static void finalizeWebDriver() {
        webDriver.quit();
    }
}
