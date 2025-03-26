package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties testprop;
	
	
	//below method is to load the properties file-which is changed to constructor
	public Base() {
	
		testprop=new Properties();
		File f1 = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
		
		//this properties is from java
		prop=new Properties();
		File f = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");

		
		try {
				//fileinput stream require file
			FileInputStream fis = new FileInputStream(f);
			prop.load(fis);
			}catch(Exception e) {
				System.out.print(e);
		}
		
		try {
		FileInputStream datafis = new FileInputStream(f1);
		testprop.load(datafis);
		}catch(Exception e) {
			System.out.print(e);
		}
	}
	
	public WebDriver initialiseBrowser(String browsername) {
		//from seleniun4.6 it is coming with drivers with inbuild so no need to add driver folder or define it again
		
		if(browsername.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
		}else if(browsername.equalsIgnoreCase("firefox")){
			driver=new FirefoxDriver();
		}else if(browsername.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
