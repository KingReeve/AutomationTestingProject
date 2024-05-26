package com.revature.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="usernameInput")
    public WebElement uInput;

    @FindBy(id="passwordInput")
    public WebElement pInput;

    @FindBy(xpath = "//input[@type='submit']")
    public WebElement submitButton;

    public void inputCredentials(String username, String password) {
        uInput.sendKeys(username);
        pInput.sendKeys(password);
    }
    public void submitAccount(){ submitButton.click();}
}
