#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@sanity
Feature: Registration New User

 Scenario Outline: Registration New User Data Driven Excel
 		Given User Launch browser
    And User navigate to home page
    And Select Register link
    When User enters mandatory fields as "<row_index>"
    And Click register button
    Then The user redirect to Registion result page

    Examples:
      |row_index|
      |1|