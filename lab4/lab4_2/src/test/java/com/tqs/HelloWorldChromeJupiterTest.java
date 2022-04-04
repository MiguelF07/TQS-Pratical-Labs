package com.tqs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class HelloWorldChromeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    private WebDriver driver; //Variable to control web browsers with Selenium WebDriver

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup(); //Before all tests, WebDriverManager is called to manage the required driver.
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(); //Before each test we create a new object of type ChromeDriver
    }

    @Test
    void test() {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl); //The test logic uses Selenium WebDriver API through the driver variable. Using the get() we're opening the practice site.
        String title = driver.getTitle(); //We get the web page title
        log.debug("The title of {} is {}", sutUrl, title); //We log the title for debugging purposes

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java"); //We verify if the web page title is what we expected
    }

    @AfterEach
    void teardown() {
        driver.quit(); //We need to close the browser at the end of each test.
    }

}
