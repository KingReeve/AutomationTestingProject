package com.revature.steps;
import com.revature.TestDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class PlanetSearchSteps {

    @When("The users enters the name of {string}")
    public void the_users_enters_the_name_of(String string) {
        TestDriver.homePage.inputToPlanetSearch(string);
    }

    @Then("The users should be able to view the Planet in the homepage {string}")
    public void the_users_should_be_able_to_view_the_Planet_in_the_homepage(String planet) throws InterruptedException {
        Thread.sleep(200);
        int tableSize = TestDriver.homePage.findSizeOfTable("planet").size();
        int searchSizeID = TestDriver.homePage.findCelestialBodyID("planet", planet).size();
        int searchSizeName = TestDriver.homePage.findCelestialBodyByName("planet", planet).size();
        Assert.assertTrue(tableSize == 1 && Math.max(searchSizeID, searchSizeName) == 1);
    }

    @When("The user enters {string}")
    public void the_user_enters(String string) {
        TestDriver.homePage.inputToPlanetSearch(string);
    }

    @Then("The user should see no change in the view of the homepage {string}")
    public void the_user_should_see_no_change_in_the_view_of_the_homepage(String planet) throws InterruptedException {
        Thread.sleep(200);
        int tableSize = TestDriver.homePage.findSizeOfTable("planet").size();
        int searchSizeID = TestDriver.homePage.findCelestialBodyID("planet", planet).size();
        int searchSizeName = TestDriver.homePage.findCelestialBodyByName("planet", planet).size();
        Assert.assertTrue(tableSize == 1 && Math.max(searchSizeID, searchSizeName) == 1);
    }

    @When("The user enters the id of {string}")
    public void the_user_enters_the_id_of(String string) {
        TestDriver.homePage.inputToPlanetSearch(string);
    }

    @Then("The user should be able to view the Planet in the homepage {string}")
    public void the_user_should_be_able_to_view_the_Planet_in_the_homepage(String planet) throws InterruptedException {
        Thread.sleep(200);
        int tableSize = TestDriver.homePage.findSizeOfTable("planet").size();
        int searchSizeID = TestDriver.homePage.findCelestialBodyID("planet", planet).size();
        int searchSizeName = TestDriver.homePage.findCelestialBodyByName("planet", planet).size();
        Assert.assertTrue(tableSize == 1 && Math.max(searchSizeID, searchSizeName) == 1);
    }

    @When("The users enters the id of {string}")
    public void the_users_enters_the_id_of(String string) {
        TestDriver.homePage.inputToPlanetSearch(string);
    }

    @Then("The users should not see any changes to the homepage {string}")
    public void the_users_should_not_see_any_changes_to_the_homepage(String planet) throws InterruptedException {
        Thread.sleep(200);
        int tableSize = TestDriver.homePage.findSizeOfTable("planet").size();
        int searchSizeID = TestDriver.homePage.findCelestialBodyID("planet", planet).size();
        int searchSizeName = TestDriver.homePage.findCelestialBodyByName("planet", planet).size();
        Assert.assertTrue(tableSize == 1 && Math.max(searchSizeID, searchSizeName) == 1);
    }
}
