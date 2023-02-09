Feature: Checkout page form

@Regression @CheckoutPage
Scenario Outline: Validate selected item on checkout page
    Given User is on Products page
    And scrolling to the product with the name <productName>
    When User adding 1 product <productName>
    And and clicking on Cart button
    Then User successfully getting to Checkout page
    And selected item <productName> on Products page is matching with the item on Checkout page
    
    Examples:
    |productName	 |
    |Jordan 6 Rings|
    
@Smoke @CheckoutPage
Scenario: Validate the total amount of selected products matching on checkout page
    Given User is on Products page
    And adding first 2 products from the list
    When User clicking on Cart button
    Then User successfully getting to Checkout page with 2 products
    And products sum on is matching with sum of 2 selected products