package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountMenu;
	
	@FindBy(linkText="Login")
	private WebElement loginButton;
	
	@FindBy(linkText="Register")
	private WebElement registerButton;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	@FindBy(name="search")
	private WebElement searchtextfield;
	
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage navigatetoLoginPage() {
		myAccountMenu.click();
		loginButton.click();
		return new LoginPage(driver);
	}
	public RegisterPage navigatetoRegisterPage() {
		myAccountMenu.click();
		registerButton.click();
		return new RegisterPage(driver);
	}
	public SearchPage clicksearchbutton() {
		searchButton.click();
		return new SearchPage(driver);
	}
	public SearchPage searchproduct(String search) {
		searchtextfield.sendKeys(search);
		searchButton.click();
		return new SearchPage(driver);
	}
}
