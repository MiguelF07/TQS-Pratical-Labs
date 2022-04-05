package com.tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
    private WebDriver driver;

    //Page URL
    private static String PAGE_URL = "https://blazedemo.com/";


    //Locators

    @FindBy(xpath="/html/body/div[3]/form/select[1]") 
    private WebElement departureCityDropdown;

    @FindBy(xpath="/html/body/div[3]/form/select[2]")
    private WebElement destinationCityDropdown;

    @FindBy(xpath="/html/body/div[3]/form/div/input")
    private WebElement findFlightButton;

    //Constructor

    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void clickOnDepartureDropdown(int op) {
        departureCityDropdown.click();
        Select dep = new Select(departureCityDropdown);
        dep.selectByIndex(op);
    }

    public void clickOnDestinationDropdown(int op) {
        destinationCityDropdown.click();
        Select dest = new Select(destinationCityDropdown);
        dest.selectByIndex(op);
    }

    public void clickOnFindFlightButton() {
        findFlightButton.click();
    }
}

