package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.pages.HomePage;
import com.tutorialninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	public SearchTest() {
		super();
	}
	
	SearchPage searchpage;
	HomePage home;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initialiseBrowser(prop.getProperty("browser"));
		home=new HomePage(driver);
	}
	
	@Test(priority=1)
	public void searchvalidproduct() {
		
		searchpage=home.searchproduct(testprop.getProperty("validproduct"));
		Assert.assertTrue(searchpage.valueisDisplayed());
	}
	
	@Test(priority=2)
	public void searchinvalidproduct() {
		searchpage=home.searchproduct(testprop.getProperty("invalidproduct"));
		Assert.assertTrue(searchpage.getsearcherrormessage().contains(testprop.getProperty("searcherrormessage")));
	}
	
	@Test(priority=3)
	public void searchwithemptydata() {
		
		searchpage=home.clicksearchbutton();
		Assert.assertTrue(searchpage.getsearcherrormessage().contains(testprop.getProperty("searcherrormessage")));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
