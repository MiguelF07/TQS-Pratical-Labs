package com.tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PurchasePage {
    private WebDriver driver;

    @FindBy(tagName = "h2")
    private WebElement heading;

    @FindBy(xpath = "//*[@id=\"inputName\"]")
    private WebElement inputName;

    @FindBy(xpath = "//*[@id=\"address\"]")
    private WebElement inputAddress;

    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement inputCity;

    @FindBy(xpath = "//*[@id=\"state\"]")
    private WebElement inputState;

    @FindBy(xpath = "//*[@id=\"zipCode\"]")
    private WebElement inputZipCode;

    @FindBy(xpath = "//*[@id=\"cardType\"]") // É dropdown
    private WebElement inputCardType;

    @FindBy(xpath = "//*[@id=\"creditCardNumber\"]")
    private WebElement inputCardNumber;

    @FindBy(xpath = "//*[@id=\"creditCardMonth\"]")
    private WebElement inputCardMonth;

    @FindBy(xpath = "//*[@id=\"creditCardYear\"]")
    private WebElement inputCardYear;

    @FindBy(xpath = "//*[@id=\"nameOnCard\"]")
    private WebElement inputNameOnCard;

    @FindBy(xpath = "//*[@id=\"rememberMe\"]")
    private WebElement rememberMeCheck;

    @FindBy(xpath = "/html/body/div[2]/form/div[11]/div/input")
    private WebElement purchaseButton;

    // Constructor
    public PurchasePage(WebDriver driver) {
        this.driver = driver;

        // Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void setName(String name) {
        inputName.clear();
        inputName.sendKeys(name);
    }

    public void setAddress(String address) {
        inputAddress.clear();
        inputAddress.sendKeys(address);
    }

    public void setCity(String city) {
        inputCity.clear();
        inputCity.sendKeys(city);
    }

    public void setState(String state) {
        inputState.clear();
        inputState.sendKeys(state);
    }

    public void setZipCode(String zipCode) {
        inputZipCode.clear();
        inputZipCode.sendKeys(zipCode);
    }

    public void setCardType(int op) {
        inputCardType.click();
        Select dep = new Select(inputCardType);
        dep.selectByIndex(op);
    }

    public void setCardNumber(String cardNumber) {
        inputCardNumber.clear();
        inputCardNumber.sendKeys(cardNumber);
    }

    public void setCardMonth(String cardMonth) {
        inputCardMonth.clear();
        inputCardMonth.sendKeys(cardMonth);
    }

    public void setCardYear(String cardYear) {
        inputCardYear.clear();
        inputCardYear.sendKeys(cardYear);
    }

    public void setNameOnCard(String nameOnCard) {
        inputNameOnCard.clear();
        inputNameOnCard.sendKeys(nameOnCard);
    }

    public void clickOnRememberCheck() {
        rememberMeCheck.click();
    }

    public void clickOnPurchaseButton() {
        purchaseButton.click();
    }

    public boolean isPageOpened() {
        // Assertion
        return heading.getText().toString().contains("Your flight from");
    }

}
