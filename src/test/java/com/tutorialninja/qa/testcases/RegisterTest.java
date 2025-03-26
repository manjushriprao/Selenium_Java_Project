package com.tutorialninja.qa.testcases;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountSuccessPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.RegisterPage;
import com.tutorialninja.qa.utils.Utilities;

public class RegisterTest extends Base {
	
	public RegisterTest() {
		super();
	}
	
	RegisterPage reg;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initialiseBrowser(prop.getProperty("browser"));
		HomePage homepage=new HomePage(driver);
		reg=homepage.navigatetoRegisterPage();
	}
	
	@Test(priority=1)
	public void verifyregisteraccountwithvaliddata() {
		
		AccountSuccessPage asp=reg.register(testprop.getProperty("firstname"), testprop.getProperty("lastname"), Utilities.generateEmailwithTimeStamp(), testprop.getProperty("telephone"), testprop.getProperty("password"), testprop.getProperty("confirmpassword"));
		String successmsg=asp.getsuccessmessage();
		Assert.assertEquals(successmsg,testprop.getProperty("successmsg"));
	}

	@Test(priority=2)
	public void registeraccoutwithoutdata() {
		
		reg.clickcontinue();
		Assert.assertTrue(reg.getprivacyError().contains(testprop.getProperty("privacywarning")));
		Assert.assertTrue(reg.getfirstnameerror().contains(testprop.getProperty("firstnamewarning")));
		Assert.assertTrue(reg.getlastnameerror().contains(testprop.getProperty("lastnamewarning")));
		Assert.assertTrue(reg.getemailerror().contains(testprop.getProperty("emailwarning")));
		Assert.assertTrue(reg.gettelephoneerror().contains(testprop.getProperty("telephonewarning")));
		Assert.assertTrue(reg.getactualpassworderror().contains(testprop.getProperty("passwordwarning")));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
