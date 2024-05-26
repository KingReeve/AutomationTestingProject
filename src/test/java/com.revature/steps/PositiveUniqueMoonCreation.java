package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.eclipse.jetty.websocket.api.WebSocketBehavior;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PositiveUniqueMoonCreation {

    //********************* Positive Tests Using Planet ID **********************************************

    @Then("The planet should exist within the celestial body list {string}")
    public void the_planet_should_exist_within_the_celestial_body_list(String planetId) {

        List<WebElement> list = TestDriver.homePage.findCelestialBodyID("planet", planetId);

        Assert.assertTrue(list.size() != 0);

    }

    @When("When The User sets the dropdown menu to Planet")
    public void when_The_User_sets_the_dropdown_menu_to_Planet() {
       TestDriver.homePage.setDropdownMenuToPlanet();
    }

    @When("The user selects Moon option")
    public void the_user_selects_Moon_option() {
       TestDriver.homePage.setDropdownMenuToMoon();
    }

    @Then("The user should see two input fields")
    public void the_user_should_see_two_input_fields() {
        WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(3));
        WebElement moonName = wait.until(ExpectedConditions.visibilityOf(TestDriver.homePage.moonNameInput));
        WebElement planetInput = wait.until(ExpectedConditions.visibilityOf(TestDriver.homePage.orbitedPlanetInput));

        Assert.assertTrue(moonName != null && planetInput != null);

    }

    @When("The user enters a unique moon name {string}")
    public void the_user_enters_a_unique_moon_name(String uniqueMoonName) {
        TestDriver.homePage.moonNameInput.sendKeys(uniqueMoonName);
    }

    @When("The user enters their planet id {string}")
    public void the_user_enters_their_planet_id(String planetId) {
        TestDriver.homePage.orbitedPlanetInput.sendKeys(planetId);
    }

    @When("The user hits the create moon button")
    public void the_user_hits_the_create_moon_button() {
        TestDriver.homePage.submitCelestial();
    }

    @Then("The user should see their new moon in the homepage {string}")
    public void the_user_should_see_their_new_moon_in_the_homepage(String moonName) {

            TestDriver.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

            List<WebElement> list = TestDriver.homePage.findCelestialBodyByName("moon", moonName);
            //Assert.assertTrue(list.get(0).getText().contains(moonName));
            Assert.assertTrue(list.size() != 0 );
        }

     // ********************************* Positive Tests Using Planet Name ************************************

    @When("The user enters their planet name {string}")
    public void the_user_enters_their_planet_name(String planetName) {
        TestDriver.homePage.orbitedPlanetInput.sendKeys(planetName);
    }




}
