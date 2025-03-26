package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentreport=new ExtentReports();
		File extentReporterFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentReporterFile);
		
		sparkreporter.config().setTheme(Theme.STANDARD);
		sparkreporter.config().setReportName("Test Automation Results Report");
		sparkreporter.config().setDocumentTitle("Automation Test Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentreport.attachReporter(sparkreporter);
		
		Properties configProp=new Properties();	
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		try {
			FileInputStream fisconfigprop=new FileInputStream(f);
			configProp.load(fisconfigprop);
		}catch(Throwable e)
		{
			e.printStackTrace();
		}
		extentreport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentreport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentreport.setSystemInfo("Email", configProp.getProperty("validemail"));
		extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentreport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentreport;
	}
}
