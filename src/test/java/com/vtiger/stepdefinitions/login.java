package com.vtiger.stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.codoid.products.exception.FilloException;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class login extends basedefinition {
	
	
@Before
public void getScenario(Scenario scen)
{
	if(extent==null)
	{
	createExtentReport();
	}
	TCName = scen.getName();
	logger = extent.createTest(TCName);
}

@After
public void savereport(Scenario scenario)
{
	extent.flush();
	
	if (scenario.isFailed()) {
        // Take screenshot and embed in report
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure Screenshot");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	driver.quit();
}
	

@Given("user should be on login page")
public void user_should_be_on_login_page() throws IOException, FilloException {
	launchApp();
  lp = new LoginPage(driver,logger);
  hp = new HomePage(driver,logger);
  ldp = new LeadPage(driver,logger);
}
@When("user enters valid credentials and click on login button")
public void user_enters_valid_credentials_and_click_on_login_button() {
         lp.login(dt.get(TCName).get("userid"), dt.get(TCName).get("password"));
}
@Then("user should be navigated to home page")
public void user_should_be_navigated_to_home_page() {
    driver.findElement(By.linkText("Home")).isDisplayed();
}
@Then("user can validate logout link")
public void user_can_validate_logout_link() {
	hp.verifyLogout();
}


@When("user enters invalid credentials and click on login button")
public void user_enters_invalid_credentials_and_click_on_login_button() {
   
    lp.login(dt.get(TCName).get("userid"), dt.get(TCName).get("password"));
    
    
  
}
@Then("user should be navigated to login page")
public void user_should_be_navigated_to_login_page() {
	lp.checkUsernameExist();
}
@Then("user can validate error message")
public void user_can_validate_error_message() {
	lp.verifyErrorMsg();
}


@When("user enters invalid userid as {string} and password as {string} click on login button")
public void user_enters_invalid_userid_as_and_password_as_click_on_login_button(String uid, String pwd) {

    lp.login(uid, pwd);
}



@Then("validate dropdown exist")
public void validate_dropdown_exist() {
	lp.checkThemeDropdownExist();
}
@Then("default selection should be {string}")
public void default_selection_should_be(String string) {
    lp.verifydafaultThemeValue(string);
}
@Then("there should four values in dropdown as {string}")
public void there_should_four_values_in_dropdown_as(String string) {
    lp.verifyalloptionValues(string);
}





}
