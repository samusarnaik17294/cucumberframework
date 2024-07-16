package com.vtiger.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.utilities.PageActions;



public class LoginPage extends PageActions {
	
	
	
	public LoginPage(WebDriver driver,ExtentTest logger)
	{
		super(driver,logger);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	WebElement uid; 
	
	@FindBy(name="user_name")
	List<WebElement> uids; 
	
	@FindBy(xpath="//input[@name='user_password']")
	WebElement pass; 
	
	@FindBy(xpath="//input[@name='Login']")
	WebElement login; 
	
	@FindBy(xpath="//img[@src=\"include/images/vtiger-crm.gif\"]")
	WebElement logo; 
	
	
	
	
	
	@FindBy(xpath="//*[contains(text(),'123You must specify a valid username and password.')]")
	WebElement err_msg; 
	
	@FindBy(name="login_theme")
	WebElement dp_theme; 
	
	/*
	private String uid = "user_name";
	private String pass = "user_password";
	*/
	
	/*
	By uid = By.name("user_name");
	By pass = By.name("user_password");
	*/
	
	
	
	
	
	public void login(String userid, String pwd)
	{
		setInput(uid, userid,userid+" has been entered into username fields");
		setInput(pass, pwd,pwd+" has been entered into password fields");
		clickElement(login,"Login button clicked");
		
	}
	
	public void checkUsernameExist()
	{
		ElementExist(uid,"Textbox username is displayed on login page");
	}
	
	public void checkThemeDropdownExist()
	{
		ElementExist(dp_theme,"Theme dropdown is displayed on login page");
	}
	
	public void verifyLogo()
	{
		ElementExist(logo,"Logo is displayed on login page");
	}
	
	public void verifyErrorMsg()
	{
		ElementExist(err_msg,"Error message validate successfully");
	}
	
	public void verifydafaultThemeValue(String theme)
	{
		Select sel = new Select(dp_theme);
		WebElement elm = sel.getFirstSelectedOption();
		Assert.assertEquals(elm.getText(), theme);		
	}
	
	public void verifyalloptionValues(String options)
	{
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		Select sel = new Select(dp_theme);
		List<WebElement> elms = sel.getOptions();
		for(WebElement e:elms)
		{
			String s = e.getText();
			if(cnt==0)
			{
			sb.append(s);
			cnt++;
			}
			else
			{
				sb.append(","+s);
			}
			
		}
		//Object[] act = elms.toArray();
		//String[] exp = options.split(",");		
		Assert.assertEquals(sb.toString(), options);		
	}
	
	
	
	

}
