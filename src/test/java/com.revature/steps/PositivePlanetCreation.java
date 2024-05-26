package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class PositivePlanetCreation {

    @Given("There is no planet with name {string}")
    public void there_is_no_planet_with_name(String planetName) {
        TestDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("planet", planetName);
        Assert.assertTrue(list.isEmpty());
    }

    @When("The user enters a {string}")
    public void the_user_enters_a(String planetName) {
        TestDriver.homePage.inputIntoOrbitedPlanetInput(planetName);
    }

    @When("The user clicks the submit button")
    public void the_user_clicks_the_submit_button() {
            TestDriver.homePage.submitCelestial();
    }

    @Then("The user should see their new planet in the homepage {string}")
    public void the_user_should_see_their_new_planet_in_the_homepage(String planetName) {
        List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("planet", planetName);
        Assert.assertFalse(list.isEmpty());
    }
}
