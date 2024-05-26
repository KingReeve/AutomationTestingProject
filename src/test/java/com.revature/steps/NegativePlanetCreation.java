package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class NegativePlanetCreation {

    //Used for Uniqueness
    @Then("The user should not see a second copy of their planet in the homepage {string}")
    public void the_user_should_not_see_a_second_copy_of_their_planet_in_the_homepage(String planetName) {
        TestDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("planet", planetName);
        Assert.assertNotEquals(2, list.size());
    }


    //Used for >30 characters
    @Then("The user should not see {string} in the homepage")
    public void the_user_should_not_see_in_the_homepage(String planetName) {
        List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("planet", planetName);
        Assert.assertTrue(list.isEmpty());
    }
}
