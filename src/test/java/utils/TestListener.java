package utils;

import io.qameta.allure.Attachment;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.IReporter;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

@Log4j2
public class TestListener implements ITestListener, IReporter {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Started test " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test " + result.getName() + " is performed successfully");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("Test " + result.getName() + " is skipped");
    }

    @Attachment(value = "Last screen state", type = "image/png")
    public void onTestFailure(ITestResult result) {
        log.info("Test " + result.getName() + " is failed");
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("webDriver");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("target/" + result.getName() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
