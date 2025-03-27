package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.AccountPage;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.LoginPage;
import com.tutorialninja.qa.utils.Utilities;

public class LoginTest extends Base{
	
	public LoginTest() {
		super();	
	}
	LoginPage loginPage;	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver=initialiseBrowser(prop.getProperty("browser"));
		HomePage homePage=new HomePage(driver);
		loginPage=homePage.navigatetoLoginPage();
	}
	
	@DataProvider(name="validcreds")
	public Object[][] supplydata(){
		
		Object[][] data=Utilities.getTestData("Login");
		return data;
		
	}
	
	@Test(priority=1,dataProvider="validcreds")
	public void verifyLoginWithValidCreds(String email, String password) {		
		AccountPage accpage=loginPage.Login(email, password);
		Assert.assertTrue(accpage.editinfooption());	
	}
	
	@Test(priority=2)
	public void loginWithinValidCreds() {	
		loginPage.Login(Utilities.generateEmailwithTimeStamp(), testprop.getProperty("invalidpassword"));
		String errorMsg=loginPage.retriveErrorMsg();
		Assert.assertEquals(errorMsg,testprop.getProperty("invalidemailerror"));
	}

	@Test(priority=3)
	public void loginWithoutEmailidandPassword() {
		loginPage.clickLoginButton();
		String errorMsg=loginPage.retriveErrorMsg();
		Assert.assertEquals(errorMsg,testprop.getProperty("invalidemailerror"));
		
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
}
