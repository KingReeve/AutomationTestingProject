package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NegativeLoginSteps {
    @When("The user can input {string} and {string}")
    public void the_user_can_input_and(String username, String password) {

        TestDriver.loginPage.enterUserCredentials(username, password);
    }

    @When("The user clicks the login button on the login page")
    public void the_user_clicks_the_login_button_on_the_login_page() {
        TestDriver.loginPage.clickLoginLink();
    }

    @Then("The user should be moved to the Home page of their account")
    public void the_user_should_be_moved_to_the_Home_page_of_their_account() {
        try {
            WebDriverWait wait = new WebDriverWait(TestDriver.driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestDriver.driver.switchTo().alert();
            String alertText = alert.getText();
            alert.dismiss();
            Assert.assertTrue(alertText.contains("login attempt failed"));
        }catch(NoAlertPresentException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
