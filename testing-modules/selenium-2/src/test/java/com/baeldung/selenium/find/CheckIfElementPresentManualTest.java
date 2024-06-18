package com.baeldung.selenium.find;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class CheckIfElementPresentManualTest {

    private WebDriver driver;
    private By element = By.cssSelector(".example");
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    @BeforeClass
    public void init() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/inputs");
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".example")));
    }

    @Test
    public void whenListOfElementsNotEmpty_thenElementIsPresent() {
        assertTrue(isElementPresentCheckByList(element), "element is not present");
    }

    @Test
    public void whenNoException_thenElementIsPresent() {
        assertTrue(isElementPresentCheckByHandleException(element), "element is not present");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private boolean isElementPresentCheckByList(By by) {
        List<WebElement> elements = driver.findElements(by);
        return !elements.isEmpty();
    }

    private boolean isElementPresentCheckByHandleException(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
