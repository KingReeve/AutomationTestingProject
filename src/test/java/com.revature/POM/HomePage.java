package com.revature.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {
    private WebDriver driver;
    private final String pageURL = "http://localhost:8010/api/webpage/home";

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //open home page
    public void openPage(){
        driver.get(pageURL);
    }

    @FindBy(xpath = "//input[@id=\"deleteInput\"]")
    public WebElement deleteField;

    @FindBy(xpath = "//button[@id=\"deleteButton\"]")
    public WebElement deleteButton;

    @FindBy(xpath = "//table")
    public WebElement celestialTable;

    @FindBy(xpath = "//select")
    public WebElement dropdownMenu;


    @FindBy(xpath = "//*[@id=\"locationSelect\"]/option[2]")
    public WebElement planetSelect;


    //finding create moon input field for moon name
    @FindBy(id="moonNameInput")
    public WebElement moonNameInput;

    //finding create moon input field for planet
    @FindBy(id="orbitedPlanetInput")
    public WebElement orbitedPlanetInput;

    @FindBy(xpath = "//input[@id=\"planetNameInput\"]")
    public WebElement planetInput;

    //getting the create new moon
    @FindBy(className = "submit-button")
    public WebElement submitButton;

    @FindBy(xpath = "//input[@id=\"searchMoonInput\"]")
    public WebElement moonSearchInput;

    @FindBy(xpath = "//button[@id=\"searchMoonButton\"]")
    public WebElement moonSearchButton;

    @FindBy(id="searchPlanetInput")
    public WebElement searchPlanetInput;

    @FindBy(id="searchPlanetButton")
    public WebElement searchPlanetButton;

    public void inputToPlanetSearch(String planetSearch)
    {
        searchPlanetInput.sendKeys(planetSearch);
    }
    public void selectPlanetSearchButton()
    {
        searchPlanetButton.click();
    }


    public void inputToDeleteField(String ID){
        deleteField.sendKeys(ID);
    }

    public void selectDeleteButton(){
        deleteButton.click();
    }

    public List<WebElement> findCelestialBodyID(String celestialBody, String ID){
        String xPathCelestialID = String.format("//table//tr/td[1][contains(text(),'%s')]/ancestor::tr/td[2][text()='%s']", celestialBody, ID);
        return driver.findElements(By.xpath(xPathCelestialID));
    }


    public List<WebElement> findCelestialBodyByName(String celestial, String name)
    {
        String xPathCelestialName = String.format("//table//tr/td[1][contains(text(),'%s')]/ancestor::tr/td[3][text()='%s']", celestial, name);
        return driver.findElements(By.xpath(xPathCelestialName));
    }

    public List<WebElement> findSizeOfTable(String celestial)
    {
        String xPathCelestialName = String.format("//table//td[1][contains(text(), '%s')]/ancestor::tr", celestial);
        return driver.findElements(By.xpath(xPathCelestialName));
    }


    public void setDropdownMenuToPlanet(){
        Select celestialSelect = new Select(dropdownMenu);
        celestialSelect.selectByValue("planet");
    }

    public void setDropdownMenuToMoon(){
        Select celestialSelect = new Select(dropdownMenu);
        celestialSelect.selectByValue("moon");
    }


    public void inputIntoOrbitedPlanetInput(String name){
        planetInput.sendKeys(name);
    }

    //method to click submit new celestial body
    public void submitCelestial()
    {
        submitButton.click();
    }

    public void inputMoonSearch(String moonName){
        moonSearchInput.sendKeys(moonName);
    }

    public void clickMoonSearchButton(){
        moonSearchButton.click();
    }

}