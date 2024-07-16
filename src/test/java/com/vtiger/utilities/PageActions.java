package com.vtiger.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.stepdefinitions.basedefinition;
import org.apache.commons.io.FileUtils;

public class PageActions {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public static ExtentTest logger;
	
	public PageActions(WebDriver driver,ExtentTest logger)
	{
		this.driver = driver;
		this.logger=logger;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
	}
	
	
	public String getScreenshot() 
	{
		Date d = new Date();
		DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
		String fileName = ft.format(d);
		String path = System.getProperty("user.dir") + "/src/test/java/com/vtiger/reports/screenshot/"+fileName+".png";
		TakesScreenshot ts = ((TakesScreenshot)driver);
		File SrcFile=ts.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(path);
		//Copy file at destination
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	public void setInput(WebElement elm , String value, String msg)
	{
		try
		{
			 wait.until(ExpectedConditions.visibilityOf(elm));
			 elm.clear();
			 elm.sendKeys(value);
			 logger.pass(msg+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("unable to entered the text in texbox due to error "+e.getMessage()+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	public void clickElement(WebElement elm, String msg)
	{
		try
		{
			 wait.until(ExpectedConditions.elementToBeClickable(elm));			 
			 elm.click();
			 logger.pass(msg+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("unable to click on element due to error "+e.getMessage()+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
	}
	
	public void ElementExist(WebElement elm, String msg)
	{
		wait.until(ExpectedConditions.visibilityOf(elm));			 
		 elm.isDisplayed();
		 logger.pass(msg+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		/*
		 try
		{
			 wait.until(ExpectedConditions.visibilityOf(elm));			 
			 elm.isDisplayed();
			 logger.pass(msg+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			logger.fail("Element not found due to error "+e.getMessage()+"&nbsp;&nbsp;&nbsp;<a href='"+getScreenshot()+"'><span class='label end-time'>Screenshot</span></a>");
		}
		*/
	}

}
