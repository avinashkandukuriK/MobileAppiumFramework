Feature: Products page form

@Regression @ProductPage
Scenario Outline: Products page default login with adding 1 product
    Given User is on Products page
    And scrolling to the product with the name <productName>
    When User adding 1 product <productName>
    And and clicking on Cart button
    Then User successfully getting to Checkout page
    
    Examples:
    |productName	 |
    |Jordan 6 Rings|
    
@Smoke @ProductPage
Scenario: Products page default login without adding product
    Given User is on Products page
    When User clicking on Cart button
    Then Toast error message for adding the product is displayed