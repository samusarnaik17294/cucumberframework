package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class LeadPage extends HeaderPage {

	public LeadPage(WebDriver driver,ExtentTest logger) {
		super(driver,logger);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name="lastname")
	WebElement tb_lname; 
	
	@FindBy(xpath="//input[@name='company']")
	WebElement tb_company; 
	
	@FindBy(xpath="//input[@name='button']")
	WebElement btn_save; 
	
	public void createLeadwithMandatoryFields(String lname, String comp)
	{
		setInput(tb_lname, lname,lname+" has been entered into lastname field");
		setInput(tb_company, comp,comp+" has been entered into company field");
		clickElement(btn_save,"Save button clicked");	
	}

}
