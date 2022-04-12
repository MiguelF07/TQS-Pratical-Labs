package io.github.bonigarcia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchChooseFlight {

    private WebDriver driver;
    private WebDriverWait wait;

    @When("I navigate to {string} website")
    public void i_navigate_to_website(String url) {
        driver = WebDriverManager.chromedriver().create();
        driver.get(url);
    }

    @When("I choose {string} as the departure city")
    public void i_choose_as_the_departure_city(String string) {
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.cssSelector("option[value=\""+string+"\"]")).click();
    }

    @When("I choose {string} as the destination city")
    public void i_choose_as_the_destination_city(String string) {
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.cssSelector("option[value=\""+string+"\"]")).click();
    }

    @When("I click on {string} button")
    public void i_click_on_button(String string) {
        driver.findElement(By.xpath("/html/body/div[3]/form/div/input")).click();
    }

    @Then("I should see the {string} page")
    public void i_should_see_the_page(String string) {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
        assertThat(driver.findElement(By.xpath("/html/body/div[2]/h3")).getText()).isEqualTo(string);
        
    }

    @Then("I choose the flight option number {int}")
    public void i_choose_the_flight_option_number(Integer option) {
        driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr["+option+"]/td[1]/input")).click();
    }

    @Then("I should see {string} on my screen")
    public void i_should_see_on_my_screen(String string) {
        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
        assertThat(driver.findElement(By.tagName("h2")).getText()).isEqualTo(string);
    }

}
