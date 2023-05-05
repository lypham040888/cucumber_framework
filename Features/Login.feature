Feature: Login with Valid Credentials

@sanity @regression
	Scenario Outline: Login page to nopcommerce
 		Given User Launch browser
    And User navigate to url "<url>"
    When User enters Email as "<username>" and Password as "<password>"
    And Click login button
    Then The user redirect to Dashboard page
    Examples:
      | url                             | username | password |
      | https://admin-demo.nopcommerce.com | admin@yourstore.com  | admin   |
