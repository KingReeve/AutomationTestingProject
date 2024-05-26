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

import static org.junit.Assert.assertTrue;

public class PlanetDeletionSteps {

    @Given("The user goes to the log in page")
    public void the_user_goes_to_the_log_in_page() {
        TestDriver.loginPage.openPage();
    }

    @When("The user can enters {string} and {string}")
    public void the_user_can_enters_and(String username, String password) {
        TestDriver.loginPage.enterUserCredentials(username, password);
    }

    @When("The user selects the login button")
    public void the_user_selects_the_login_button() {
        TestDriver.loginPage.clickLoginLink();
    }

    @Then("The user should be redirected to the Home page for their account")
    public void the_user_should_be_redirected_to_the_Home_page_for_their_account() {
        try{
            WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.titleIs("Home"));
            Assert.assertEquals("Home", TestDriver.driver.getTitle());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


    @When("The User sets the dropdown menu to Planet.")
    public void theUserSetsTheDropdownMenuToPlanet() {
        TestDriver.homePage.planetSelect.click();
    }

    @When("The User enters a {string} in the Delete Field")
    public void the_User_enters_a_in_the_Delete_Field(String ID) {
        TestDriver.homePage.inputToDeleteField(ID);
    }

    @When("The User selects the Delete Button")
    public void the_User_selects_the_Delete_Button() {
        TestDriver.homePage.selectDeleteButton();
    }

    @Then("Planet with ID {string} is Deleted from the Database")
    public void the_Planet_is_Deleted_from_the_Database(String ID) throws InterruptedException {
        Thread.sleep(200);
        List<WebElement> CelestialExistence = TestDriver.homePage.findCelestialBodyID("planet", ID);
        Assert.assertEquals(0, CelestialExistence.size());
    }
    @Then("Planet with Name {string} is Deleted from the Database")
    public void planet_with_Name_is_Deleted_from_the_Database(String name) throws InterruptedException {
        Thread.sleep(200);
        List<WebElement> CelestialExistence = TestDriver.homePage.findCelestialBodyByName("planet", name);
        Assert.assertEquals(0, CelestialExistence.size());
    }

    //Negative deletion steps

    @When("The User enters a {string} in the Delete Field that belongs to another User")
    public void the_User_enters_a_in_the_Delete_Field_that_belongs_to_another_User(String ID)  {
        TestDriver.homePage.inputToDeleteField(ID);
    }

    @Then("Planet with ID {string} is not Deleted from the Database")
    public void planet_with_ID_is_not_Deleted_from_the_Database(String ID) throws InterruptedException {
        Thread.sleep(200);
        List<WebElement> CelestialExistence = TestDriver.homePage.findCelestialBodyID("planet", ID);
        Assert.assertEquals(1, CelestialExistence.size());
    }

}