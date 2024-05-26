package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class NegativeMoonCreationSteps {

//********************************** Negative Steps with Planet ID ************************************************************

    //non unique name step definitions
    @When("The user enters a non-unique moon name {string}")
    public void the_user_enters_a_non_unique_moon_name(String nonUniqueMoonName) {
        TestDriver.homePage.moonNameInput.sendKeys(nonUniqueMoonName);
    }

    @Then("The user should not see two moons of the same name in the homepage {string}")
    public void the_user_should_not_see_two_moons_of_the_same_name_in_the_homepage(String moonName) {
        TestDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("moon", moonName);
        //Assert.assertTrue(list.get(0).getText().contains(moonName));
        Assert.assertEquals(1, list.size());
    }

    // non existing planet step definitions
    @When("The user enters the non-existing planet id {string}")
    public void the_user_enters_the_non_existing_planet_id(String planetID) {
        TestDriver.homePage.orbitedPlanetInput.sendKeys(planetID);
    }

    @Then("The user should not see their new moon in the homepage {string}")
    public void the_user_should_not_see_their_new_moon_in_the_homepage(String moonName) {
        TestDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("moon", moonName);
        //Assert.assertTrue(list.get(0).getText().contains(moonName));
        Assert.assertEquals(0, list.size());
    }

    // too long moon name definition
    @When("The user enters a unique long moon name {string}")
    public void the_user_enters_a_unique_long_moon_name(String longMoonName) {
        TestDriver.homePage.moonNameInput.sendKeys(longMoonName);
    }

    //********************************** Negative Steps with Planet Name ************************************************************
    @When("The user enters the non-existing planet name {string}")
    public void the_user_enters_the_non_existing_planet_name(String planetName) {
        TestDriver.homePage.orbitedPlanetInput.sendKeys(planetName);
    }
}
