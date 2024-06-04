package com.revature;

import com.revature.POM.HomePage;
import com.revature.POM.LoginPage;
import com.revature.POM.RegistrationPage;
import com.revature.utilities.ConnectionUtil;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// TODO: Add features folders path, glue which tells cucumber where the steps are, and plugin for test results
@RunWith(Cucumber.class)
@CucumberOptions(
        // in maven projects, resources directories are added to the class path
        features = "classpath:features",//tells cucumber to find a directory called features in the class path

        //glue tells cucumber where the steps or the code associated with the scenario accpetance criteria are located
        //By target a package we are telling cucumber to look at the classes located in the package to find the steps
        glue = "com.revature.steps",
        //plugin is where we can provide extra configuration for cucumber to use
        //here we are telling cucumber to use "pretty" option to make tests reports generated look nice
        //and the generated report will be generated as an html file in the given path
        plugin = {
                "pretty",
                "html:src/test/resources/reports/html-reports.html",
                "json:src/test/resources/reports/json-reports.json",
                "junit:src/test/resources/reports/xml-reports.xml"
        }
//        , tags = "@RegistrationPositive or @RegistrationNegative"
)

public class TestDriver {
    // web driver instance
    public static WebDriver driver;

    public static LoginPage loginPage;

    public static RegistrationPage regPage;

    public static HomePage homePage;



    //before anything else
    @BeforeClass
    public static void setUp(){
        //init driver
        setupDriver();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        ConnectionUtil.resetTestDatabase();

    }

    public static void setupDriver()
    {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}