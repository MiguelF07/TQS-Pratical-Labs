package io.cucumber.skeleton;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
    Belly belly = new Belly();
    @Given("I have {int} cukes in my belly")
    public void I_have_cukes_in_my_belly(int cukes) {
        belly.eat(cukes);
    }

    @When("I wait {int} hour")
    public void iWaitXhours(int hour) {
        belly.wait(hour);
    }

    @Then("my belly should growl")
    public void bellyShoudGrowl() {
        if(belly.bellySize()==0) {
            System.out.println("GRRRR");
        }
    } 
}