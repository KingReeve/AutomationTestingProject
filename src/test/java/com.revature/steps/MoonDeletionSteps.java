package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MoonDeletionSteps {

    @Then("Moon with ID {string} is Deleted from the Database")
    public void moon_with_ID_is_Deleted_from_the_Database(String ID) throws InterruptedException{
        Thread.sleep(200);
        List<WebElement> CelestialExistence = TestDriver.homePage.findCelestialBodyID("moon", ID);
        Assert.assertEquals(0, CelestialExistence.size());
    }

    @Then("Moon with ID {string} is not Deleted from the Database")
    public void moon_with_ID_is_not_Deleted_from_the_Database(String ID) throws InterruptedException{
        Thread.sleep(200);
        List<WebElement> CelestialExistence = TestDriver.homePage.findCelestialBodyID("moon", ID);
        Assert.assertEquals(1, CelestialExistence.size());
    }

}