package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.utilities.PageActions;

public class HeaderPage extends PageActions {
	
	

	public HeaderPage(WebDriver driver,ExtentTest logger)
	{
		super(driver,logger);		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Home")
	WebElement lnk_Home; 
	
	@FindBy(linkText="Logout")
	WebElement lnk_Logout; 
	
	@FindBy(linkText="Leads")
	WebElement lnk_Leads; 
	
	@FindBy(linkText="New Lead")
	WebElement lnk_NewLead; 
	
	
	public void verifyHome()
	{
		ElementExist(lnk_Home,"Home menu is displayed on home page");
	}
	
	public void verifyLogout()
	{
		ElementExist(lnk_Logout,"Logout link is displayed on home page");
	}
	
	public void ClickLogout()
	{
		clickElement(lnk_Logout,"logout link cliked");
	}
	
	public void ClickLeads()
	{
		clickElement(lnk_Leads,"Leads menu cliked");
	}
	
	public void ClickNewLead()
	{
		clickElement(lnk_NewLead,"New Lead menu cliked");
	}
	
	

}
