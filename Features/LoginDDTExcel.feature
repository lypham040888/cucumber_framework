Feature: Login Data Driven with Excel


@sanity @regression
	Scenario Outline: Login page to nopcommerce Data Driven Excel
 		Given User Launch browser
    And User navigate to admin url
    When User enters Email and Password as "<row_index>"
    And Click login button
    Then The user redirect to Dashboard page

    Examples:
      |row_index|
      |1|
      |2|
			|3|