package com.vtiger.stepdefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LeadsSteps extends basedefinition {
	
	

@When("once user create the lead")
public void once_user_create_the_lead(io.cucumber.datatable.DataTable dataTable) {
	
	List<Map<String,String>> ls = dataTable.asMaps();
	for(Map<String,String> m : ls)
	{
		
		ldp.ClickNewLead();
		ldp.createLeadwithMandatoryFields(m.get("lastname"), m.get("company"));		
	}
   
}
@Then("lead should be created successfully")
public void lead_should_be_created_successfully() {
   
}
@Then("user click on logout")
public void user_click_on_logout() {
   ldp.ClickLogout();
}

@When("once user create the lead with mandatory fields")
public void once_user_create_the_lead_with_mandatory_fields() {
	ldp.ClickNewLead();
	ldp.createLeadwithMandatoryFields(dt.get(TCName).get("LastName"), dt.get(TCName).get("Company"));
}

}
