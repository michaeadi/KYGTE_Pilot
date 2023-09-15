package org_automation.Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org_automation.base.PageObjects;
import org_automation.base.SeleniumUtils;
import org_automation.base.TestData;
import java.util.List;
import java.util.Random;

public class Login {
	private ExtentReports extent;
	private ExtentTest login_Test;
	private ExtentTest homePage_Test;
	private ExtentTest createProduct_Test;
	private ExtentTest createAttestation_Test;
	private WebDriver driver;
	private Actions actions;
	private SeleniumUtils seleniumUtils;

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
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/index.html");
		extent.attachReporter(spark);
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		driver.quit();
	}

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeMethod
	public void beforeMethod() {
		login_Test = extent.createTest("LoginTest");
		homePage_Test = extent.createTest("HomePageTest");
		createProduct_Test = extent.createTest("CreateProductTest");
		createAttestation_Test = extent.createTest("CreateAttestationTest");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			String testName = result.getMethod().getMethodName();
			String screenshotName = "Failure_" + testName;
			login_Test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
			seleniumUtils.captureScreenshotAndLog(login_Test, screenshotName);
		}
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}


	@Test(priority = 1)
	public void homePageElements(){
		/**
		 * Test logic for verifying home page elements
		 */
		/**
		 * Creating an instance of WebDriverWait with a timeout of 10 seconds
		 */

		WebDriverWait wait = new WebDriverWait(driver, 20);

		By[] elementLocators = {
				PageObjects.kygLogo,
				PageObjects.attrDef,
				PageObjects.userGuide,
				PageObjects.contactUs,
				PageObjects.horizLogo,
				PageObjects.productCode,
				PageObjects.searchRegB,
				PageObjects.createNewB,
				PageObjects.advancedSearchB
		};

		String[] elementDescriptions = {
				"KYG Logo is present on the page.",
				"Attribute Definition Icon is present on the page.",
				"User Guide Icon is present on the page.",
				"Contact US Icon is present on the page.",
				"Horizontal KYG Logo is present on the page.",
				"Product Code Field is present on the page.",
				"Search Registration Button Icon is present on the page.",
				"Create New KYGID Button is present on the page.",
				"Advanced Search Button is present on the page."
		};

		for (int i = 0; i < elementLocators.length; i++) {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocators[i]));
			Assert.assertNotNull(element, elementDescriptions[i]);
			homePage_Test.pass(elementDescriptions[i] + " is displayed successfully");
		}


	}
	@Test (priority = 2)
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
		actions.sendKeys(emailField,TestData.initiatorEmail).perform();
		login_Test.pass("Email data is parsed");

		/**
		 * Wait for the Login Page to be visible and Password field displays
		 */
		WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.passwordField));


		/**
		 * Send Test Password to Password field
		 */
		actions.sendKeys(passwordField,TestData.initiatorPassword).perform();
		login_Test.pass("Password is parsed successfully");

		/**
		 * Wait for the Login Page to be visible and Login Button displays
		 */
		WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.loginButton));

		/**
		 * Perform actions on Login Button
		 */
		actions.click(loginButton).perform();
		login_Test.pass("Login button is functional");
		login_Test.pass("User is logged in successfully");

	}


	@Test (priority = 3)
	public void createProduct(){

		/**
		 * Create an instance of WebDriverWait with a timeout of 10 seconds
		 */

		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement newItemBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.newItemBtn));
		actions.click(newItemBtn).perform();


		/**
		 * randomly selecting the available Item Types
		 **/

		WebElement ulItemTypes = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.itemTypes));

		List<WebElement> liItemTypes = ulItemTypes.findElements(By.tagName("li"));

		Random rand = new Random();
		int randomIndex = rand.nextInt(liItemTypes.size());

		liItemTypes.get(randomIndex).click();


		WebElement itemName = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.itemName));
		actions.sendKeys(itemName,TestData.name).perform();
		createProduct_Test.pass("Item Name is parsed successfully");


		WebElement itemDesc = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.itemDesc));
		actions.sendKeys(itemDesc,TestData.desc).perform();
		createProduct_Test.pass("Item Description is parsed successfully");


		WebElement itemNum = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.itemNum));
		actions.sendKeys(itemNum,TestData.num).perform();
		createProduct_Test.pass("Item Number is parsed successfully");

		WebElement itemRev = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.itemRev));
		actions.sendKeys(itemRev,TestData.rev).perform();
		createProduct_Test.pass("Item Revison is parsed successfully");


		WebElement createProdBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.createProdBtn));
		actions.click(createProdBtn).perform();
		login_Test.pass("Create button is functional");
		login_Test.pass("Product is created successfully");


		WebElement prodWrapper = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.prodWrapper));
		Assert.assertTrue(prodWrapper.isDisplayed(), "Product Attributes Wrapper element is displayed.");

	}

	@Test (priority = 4)
	public void createECNAttestation(){
		/**
		 * Create an instance of WebDriverWait with a timeout of 10 seconds
		 */

		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement selectKYGID = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.selectKYGID));
		actions.click(selectKYGID).perform();


		List<WebElement> buttonElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@id=\"create-button\"]")));
		if (buttonElements.size() >= 3) {
			WebElement createButton = buttonElements.get(2);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
		}


		WebElement createECN = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.createECN));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", createECN);
		createAttestation_Test.pass("ECN Attestation is created successfully");


	}


	@Test (priority = 5)
	public void createHSAttestation(){
		/**
		 * Create an instance of WebDriverWait with a timeout of 10 seconds
		 */

		WebDriverWait wait = new WebDriverWait(driver, 20);

		List<WebElement> buttonElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@id=\"create-button\"]")));
		if (buttonElements.size() >= 3) {
			WebElement createButton = buttonElements.get(2);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", createButton);
		}

		WebElement createHS = wait.until(ExpectedConditions.visibilityOfElementLocated(PageObjects.createHS));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", createHS);
		createAttestation_Test.pass("HS Attestation is created successfully");

	}

}
