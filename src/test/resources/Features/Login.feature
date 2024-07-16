@regression
Feature: Login functionality

Background: 
Given user should be on login page

@login1
Scenario: valid_login_TC01
When user enters valid credentials and click on login button
Then user should be navigated to home page
And user can validate logout link




@invalidlogin
Scenario: Invalid_login_TC02
When user enters invalid credentials and click on login button
Then user should be navigated to login page
And user can validate error message

@run @test1  @sanity
Scenario Outline: Invalid login with dataset
When user enters invalid userid as "<userid>" and password as "<password>" click on login button
Then user should be navigated to login page
And user can validate error message
Examples:
|userid | password |
|admin1  |pwd1 |
|admin2  |pwd2 |
|admin3  | pwd3 |

@dropdown
Scenario Outline: validate_login_theme_dropdown_TC05
Then validate dropdown exist
And default selection should be "<Default_value>"
And there should four values in dropdown as "<options>"
Examples:
|Default_value | options |
| blue         | Aqua,blue,nature,orange| 



