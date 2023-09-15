package org_automation.base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SeleniumUtils {
    private WebDriver driver;

    public SeleniumUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshotAndLog(ExtentTest test, String screenshotName) {
        // Capture screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        // Set the location where the screenshot will be saved
        String screenshotLocation = "screenshots/";

        // Create the screenshots directory if it doesn't exist
        File screenshotsDir = new File(screenshotLocation);
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdir();
        }

        // Set the file name with timestamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = now.format(formatter);
        String fileName = screenshotName + "_" + timestamp + ".png";

        // Set the final path of the screenshot
        String finalPath = screenshotLocation + fileName;

        try {
            // Copy the screenshot file to the final location
            org.apache.commons.io.FileUtils.copyFile(srcFile, new File(finalPath));
            // Attach the screenshot to the Extent Report
            test.fail("Test Failed. Please see the screenshot below: " + test.addScreenCaptureFromPath(finalPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void waitForElementVisibility(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
