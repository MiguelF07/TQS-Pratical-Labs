package com.tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightsPage {
    
    private WebDriver driver;

    // Locators

    @FindBy(tagName = "h3")
    WebElement heading;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[1]/input")
    private WebElement button1;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[2]/td[1]/input")
    private WebElement button2;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[3]/td[1]/input")
    private WebElement button3;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[4]/td[1]/input")
    private WebElement button4;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[5]/td[1]/input")
    private WebElement button5;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //We will use this boolean for assertion. To check if page is opened
   public boolean isPageOpened(){
    return heading.getText().toString().contains("Flights from");
}

    public void clickOnButton(int n) {
        switch (n) {
            case 1:
                button1.click();
                break;
            case 2:
                button2.click();
                break;
            case 3:
                button3.click();
                break;
            case 4:
                button4.click();
                break;
            case 5:
                button5.click();
                break;
        }
    }
}
