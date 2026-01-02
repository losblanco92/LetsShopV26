Feature: Feature: Account Registration
  As a new user
  I want to register an account
  So that I can access the application

  Scenario Outline: Postive test for successful account registration
    Given I navigate to the registration page
    When I enter first name <firstName>
    And I enter last name <lastName>
    And I enter email <email>
    And I enter phone number <phoneNumber>
    And I enter password <password>
    And I confirm password <password>
    And I tick the terms and conditions checkbox
    And I click on the Register button
    Then I should see the message "Account Created Successfully"

    Examples: 
      | firstName | lastName | email                | phoneNumber | password   |
      | Neer      | Singh    | neer1234@test.com    |  9876543210 | Test@12345 |
      | Tanu      | Sharma   | tanusharma1@test.com |  9123456789 | Pass@56789 |
