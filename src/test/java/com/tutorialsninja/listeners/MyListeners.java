package com.tutorialsninja.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.tutorialsninja.base.BaseTest;
import com.tutorialsninja.util.ScreenshotHelper;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {

    ExtentReports extentReport;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {

        String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(pathOfExtentReport);

        spark.config().setReportName("Automation Results");
        spark.config().setDocumentTitle("Test Execution Report");

        extentReport = new ExtentReports();
        extentReport.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {

        extentTest = extentReport.createTest(result.getName());
        extentTest.log(Status.INFO,result.getName()+" started executing");

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        extentTest.log(Status.PASS,result.getName()+" got successfully executed");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;

        driver = ((BaseTest) result.getInstance()).getDriver();

        if (driver != null) {
            String destinationScreenshotPath = ScreenshotHelper.captureScreenshot(driver, result.getName());
            extentTest.addScreenCaptureFromPath("../../"+destinationScreenshotPath);
            extentTest.log(Status.FAIL, "Screenshot attached for failure: " + result.getName());
        }

        extentTest.log(Status.FAIL, result.getName() + " failed!");
        extentTest.log(Status.INFO, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        extentTest.log(Status.INFO,result.getThrowable());
        extentTest.log(Status.SKIP, result.getName()+" got skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReport != null) {
            extentReport.flush();
        }

        String path = System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
        File finalReport = new File(path);

        if (finalReport.exists()) {
            try {
                Desktop.getDesktop().browse(finalReport.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
