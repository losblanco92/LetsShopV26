

Feature: Login Data Driven with Excel
  
  Scenario Outline: Check if user is able to login with valid credentials
 Given User enters the vaild email and password from excel row <row_index>
  Then the user should be successfully logged in when valid credentails are entered

    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
      |         5 |
