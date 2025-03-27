package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentReporter;
import com.tutorialninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentreport;
	ExtentTest extentTest;
	String testName;
	@Override
	public void onStart(ITestContext context) {
		 extentreport = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		testName=result.getName();
		extentTest = extentreport.createTest(testName);
		extentTest.log(Status.INFO, testName+ " test started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, testName+ " got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver=null;
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		
		String destScreenshotPath=Utilities.takeScreenshot(driver, testName);
		
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+ " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+ " got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
