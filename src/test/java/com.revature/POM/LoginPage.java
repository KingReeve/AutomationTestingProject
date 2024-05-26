package com.revature.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static WebDriver driver;
    private final String pageURL = "http://localhost:8010/webpage/login";

    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //opening login page
    public void openPage(){
        driver.get(pageURL);
    }


    //getting account creation link
    @FindBy(xpath = "//a")
    public WebElement createLink;
    //redirecting to account creation link
    public void clickCreateLink()
    {
        createLink.click();
    }
    @FindBy(id = "usernameInput")
    public WebElement usernameInput;

    @FindBy(id = "passwordInput")
    public WebElement passwordInput;
    public void enterUserCredentials(String username, String password)
    {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    @FindBy(xpath = "/html/body/div/form/input[3]")
    public WebElement loginLink;

    public void clickLoginLink()
    {
        loginLink.click();
    }
}