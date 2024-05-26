package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PositiveMoonSearch {

    @When("The user inputs {string}")
    public void the_user_inputs(String moonQuery) {
        WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(TestDriver.homePage.moonSearchInput));
        wait.until(ExpectedConditions.visibilityOf(TestDriver.homePage.moonSearchButton));

        TestDriver.homePage.inputMoonSearch(moonQuery);
    }

    @When("The user clicks the Moon Search button.")
    public void the_user_clicks_the_Moon_Search_button() {
        TestDriver.homePage.clickMoonSearchButton();
    }

    @Then("The user should see only the moon {string}")
    public void the_user_should_see_only_the_moon(String moonQuery) throws InterruptedException {
//        Thread.sleep(300);
        int tableSize = TestDriver.homePage.findSizeOfTable("moon").size();
        int searchSizeID = TestDriver.homePage.findCelestialBodyID("moon", moonQuery).size();
        int searchSizeName = TestDriver.homePage.findCelestialBodyByName("moon", moonQuery).size();
        Assert.assertTrue(tableSize == 1 && Math.max(searchSizeID, searchSizeName) == 1);
    }
}