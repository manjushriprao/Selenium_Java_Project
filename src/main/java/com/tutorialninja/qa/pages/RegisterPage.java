package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstname;
	
	@FindBy(id="input-lastname")
	private WebElement lastname;
	
	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-telephone")
	private WebElement telephone;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(id="input-confirm")
	private WebElement confirmpassword;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement subscribe;
	
	@FindBy(name="agree")
	private WebElement agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continuebutton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyError;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameError;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameError;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailError;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneError;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement actualpasswordError;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	
	public AccountSuccessPage clickcontinue() {
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}
	
	public AccountSuccessPage register(String firstnametext,String lastnametext,String emailtext,String telephonetext,String passwordtext, String confirmpasswordtext) {
		firstname.sendKeys(firstnametext);
		lastname.sendKeys(lastnametext);
		email.sendKeys(emailtext);
		telephone.sendKeys(telephonetext);
		password.sendKeys(passwordtext);
		confirmpassword.sendKeys(confirmpasswordtext);
		subscribe.click();
		agree.click();
		continuebutton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String getprivacyError() {
		String privacyErrormsg=privacyError.getText();
		return privacyErrormsg;
	}
	public String getfirstnameerror() {
		String firstnameerrormsg=firstNameError.getText();
		return firstnameerrormsg;
	}
	public String getlastnameerror() {
		String lastnameerrormsg=lastNameError.getText();
		return lastnameerrormsg;
	}
	public String getemailerror() {
		String emailerrormsg=emailError.getText();
		return emailerrormsg;
	}
	public String gettelephoneerror() {
		String telephoneerrormsg=telephoneError.getText();
		return telephoneerrormsg;
	}
	public String getactualpassworderror() {
		String actualpassworderrormsg=actualpasswordError.getText();
		return actualpassworderrormsg;
	}
	
}
