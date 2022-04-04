package com.tqs;

import com.tqs.webpages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import com.tqs.webpages.FlightsPage;
import com.tqs.webpages.PurchasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookAFlightTest {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void bookAFlight() {
        HomePage home = new HomePage(driver);
        home.clickOnDepartureDropdown(4);
        home.clickOnDestinationDropdown(2);
        home.clickOnFindFlightButton();

        FlightsPage flightsPage = new FlightsPage(driver);

        assertTrue(flightsPage.isPageOpened());

        flightsPage.clickOnButton(2);

        PurchasePage purchasePage = new PurchasePage(driver);

        assertTrue(purchasePage.isPageOpened());

        purchasePage.setName("Carl");
        purchasePage.setAddress("123 Main St.");
        purchasePage.setCity("New York");
        purchasePage.setState("New York");
        purchasePage.setZipCode("18472");
        purchasePage.setCardType(1);
        purchasePage.setCardNumber("4836274839283948");
        purchasePage.setCardMonth("12");
        purchasePage.setCardYear("2024");
        purchasePage.setNameOnCard("Carl Johnson");
        purchasePage.clickOnRememberCheck();
        purchasePage.clickOnPurchaseButton();
    }

    @AfterEach
    public void close(){
          driver.close();
   }
}
