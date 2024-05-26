package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationNegativeSteps {
    public RegistrationNegativeSteps(){
    }

    @When("The user enters invalid {string} and {string}")
    public void the_user_enters_invalid_and(String username, String password) {
        TestDriver.regPage.inputCredentials(username, password);
    }

    @When("The user clicks upon create account button")
    public void the_user_clicks_upon_create_account_button() {
        TestDriver.regPage.submitAccount();
    }

    @Then("The user should not receive an alert stating account creation successful")
    public void the_user_should_not_receive_an_alert_stating_account_creation_successful() {
        try {
            WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestDriver.driver.switchTo().alert();
            String alertText = alert.getText();
            alert.dismiss();
            Assert.assertFalse(alertText.contains("Account created successfully"));
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("The user should not be redirected to the log in page")
    public void the_user_should_not_be_redirected_to_the_log_in_page() {
        try {
            Assert.assertNotEquals("Planetarium Login", TestDriver.driver.getTitle());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}