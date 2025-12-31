@tag
Feature: Successfully place an order on the website
 
 Background:
 Given I landed on Ecommerce page

  Scenario Outline: Positive test for successfully placing an order
    Given Logged in with username <name> and password <password>
    When I add product <product> to the cart
    And  Go to cart page 
    And  Checkout 
    And Enter payment details and place order
    Then "THANK YOU FOR THE ORDER." message is displayed on the confirmation page

    Examples: 
      | name           | password | product         |
      | neer@yahoo.com | Test@123 | ADIDAS ORIGINAL |
