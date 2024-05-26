package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PositiveLoginSteps {
    @When("The user can enter {string} and {string}")
    public void the_user_can_enter_and(String username, String password) {
        WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(TestDriver.loginPage.passwordInput));
        wait.until(ExpectedConditions.visibilityOf(TestDriver.loginPage.usernameInput));

        TestDriver.loginPage.enterUserCredentials(username, password);
    }

    @When("The user clicks the login button")
    public void the_user_clicks_the_login_button() {
        TestDriver.loginPage.clickLoginLink();
    }

    @Then("The user should be redirected to the Home page of their account")
    public void the_user_should_be_redirected_to_the_Home_page_of_their_account() {
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

}
