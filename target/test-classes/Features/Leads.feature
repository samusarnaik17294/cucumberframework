@regression
Feature: Login functionality

@lead
Scenario: create_multiple_leads_TC04
Given user should be on login page
When user enters valid credentials and click on login button
And once user create the lead
|lastname | company |
|admin1  |pwd1 |
|admin2  |pwd2 |
|admin3  | pwd3 |
Then lead should be created successfully
And user click on logout


@excel
Scenario: create_leads_TC03
Given user should be on login page
When user enters valid credentials and click on login button
And once user create the lead with mandatory fields
Then lead should be created successfully
And user click on logout