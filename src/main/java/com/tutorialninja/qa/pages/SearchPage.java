package com.tutorialninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;
	
	@FindBy(linkText="HP LP3065")
	private WebElement validhpproduct;
	
	@FindBy(xpath="//input[@id='button-search']/following-sibling::p")
	private WebElement searchErrormessage;
	
	public SearchPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean valueisDisplayed() {
		boolean displaystatus=validhpproduct.isDisplayed();
		return displaystatus;
	}
	public String getsearcherrormessage() {
		String searcherrormsg=searchErrormessage.getText();
		return searcherrormsg;
	}
	

}
