package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;


import org.openqa.selenium.support.ui.ExpectedCondition;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationSteps {
    public RegistrationSteps(){
    }

    @Given("The user is in the log in page")
    public void the_user_is_in_the_log_in_page() {
        TestDriver.loginPage.openPage();
    }

    @When("The user clicks the Create an Account link")
    public void the_user_clicks_the_Create_an_Account_link() {
        TestDriver.loginPage.clickCreateLink();
    }

    @Then("The user should be redirected to the Create An Account Page")
    public void the_user_should_be_redirected_to_the_Create_An_Account_Page() {
        Assert.assertEquals("Account Creation", TestDriver.driver.getTitle());
    }

    @When("The user enters {string} and {string}")
    public void the_user_enters_username_and_password(String username, String password) {
        TestDriver.regPage.inputCredentials(username, password);
    }

    @When("The user clicks on create account button")
    public void the_user_clicks_on_create_account_button() {
        TestDriver.regPage.submitAccount();
    }
    @Then("The user should receive an alert stating account creation successful")
    public void the_user_should_receive_an_alert_stating_account_creation_successful(){
        try {
            WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestDriver.driver.switchTo().alert();
            String alertText = alert.getText();
            alert.dismiss();
            Assert.assertTrue(alertText.contains("Account created successfully"));
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Then("The user should be redirected to the log in page")
    public void the_user_should_be_redirected_to_the_log_in_page() {
        Assert.assertEquals("Planetarium Login", TestDriver.driver.getTitle());
    }
}