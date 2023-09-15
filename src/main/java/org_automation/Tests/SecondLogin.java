package org_automation.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org_automation.base.PageObjects;
import org_automation.base.TestData;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SecondLogin {
    private ExtentReports extent;
    private ExtentTest login_Test;
    private ExtentTest homePage_Test;

    private WebDriver driver;
    private Actions actions;

    @BeforeSuite
    public void beforeSuite() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(PageObjects.pageURL);
        driver.manage().window().maximize();
        actions = new Actions(driver);

        /**
         * Creating an Extent Report for the test
         */
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
        driver.quit();
    }

    @BeforeMethod
    public void setUp() {
        login_Test = extent.createTest("LoginTest");
        homePage_Test = extent.createTest("HomePageTest");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(result.getName()); // Capture screenshot on test failure
        }
    }

    @Test
    public void homePageElements() {
        /**
         * Test logic for verifying home page elements
         */
        /**
         * Creating an instance of WebDriverWait with a timeout of 10 seconds
         */
        WebDriverWait wait = new WebDriverWait(driver, 10);

        /**
         * Waiting for the elements to be visible
         */

        WebElement kygLogo = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.kygLogo));

        /**
         * Assert that the elements are present
         */

        Assert.assertNotNull(kygLogo, "KYG Logo is present on the page.");
        homePage_Test.pass("KYG Logo is displayed successfully");
    }

    @Test
    public void loginTest() {
        /**
         * Create an instance of WebDriverWait with a timeout of 10 seconds
         */
        WebDriverWait wait = new WebDriverWait(driver, 10);

        /**
         * Wait for the Login Link on Header to be visible
         */

        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.loginHeader));

        /**
         * Perform actions on the Login Link
         */

        actions.click(loginLink).perform();
        login_Test.pass("Login Link is clicked successfully");

        /**
         * Wait for the Login Page to be visible and Email field displays
         */

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.emailField));

        /**
         * Send Test Email to Email field
         */
        actions.sendKeys(emailField, TestData.initiatorEmail).perform();
        login_Test.pass("Email data is parsed");

        /**
         * Wait for the Login Page to be visible and Password field displays
         */

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.passwordField));

        /**
         * Send Test Password to Password field
         */
        actions.sendKeys(passwordField, TestData.initiatorPassword).perform();
        login_Test.pass("Password is parsed successfully");

        /**
         * Wait for the Login Page to be visible and Login Button displays
         */
        WebElement loginButton = driver.findElement(PageObjects.loginButton);

        /**
         * Perform actions on Login Button
         */
        actions.click(loginButton).perform();
        login_Test.pass("Login button is functional");
        login_Test.pass("User is logged in successfully");
    }

    /**
     * Captures a screenshot and attaches it to the extent report
     * @param loginScreenshot The name to be given to the screenshot file
     */

    private void captureScreenshot(String loginScreenshot) {

        /**
         * Convert driver to TakesScreenshot
         */
        TakesScreenshot ts = (TakesScreenshot) driver;

        /**
         * Capture screenshot as File
         */
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        /**
         * Set the location where the screenshot will be saved
         */
        String screenshotLocation = System.getProperty("user.dir") + "/screenshots/";

        /**
         * Create the screenshots directory if it doesn't exist
         */
        File screenshotsDir = new File(screenshotLocation);
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdir();
        }

        /**
         * Set the file name with timestamp
         */
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);
        String fileName = loginScreenshot + "_" + timestamp + ".png";

        /**
         * Set the final path of the screenshot
         */
        String finalPath = screenshotLocation + fileName;
        try {
            // Copy the screenshot file to the final location
            FileUtils.copyFile(srcFile, new File(finalPath));
            // Attach the screenshot to the Extent Report
            login_Test.fail("Test Failed. Please see the screenshot below: "
                    + login_Test.addScreenCaptureFromPath("screenshots/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

