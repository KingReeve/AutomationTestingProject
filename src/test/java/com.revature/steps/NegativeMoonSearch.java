package com.revature.steps;

import com.revature.TestDriver;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class NegativeMoonSearch {
    @Then("The user should see nothing")
    public void the_user_should_see_nothing() throws InterruptedException {
//        Thread.sleep(300);
        int tableSize = TestDriver.homePage.findSizeOfTable("moon").size();
        Assert.assertEquals(0, tableSize);
    }
}