package com.tqs;

import com.tqs.webpages.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import com.tqs.webpages.FlightsPage;
import com.tqs.webpages.PurchasePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class BookAFlightTest {
    private WebDriver driver;

    @Test
    public void bookAFlight(ChromeDriver driver) {
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

}
