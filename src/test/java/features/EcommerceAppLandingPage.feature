Feature: Landing page form

@Regression @LandingPage
Scenario Outline: Landing page default login without the name
    Given User is on General Store landing page
    When User choosing his country <country> from dropdown
    And and his Gender <gender> from checkboxes
    And and clicking on Lets shop button
    Then Toast error message for entering the name is displayed
    
    Examples:
    |country	 |gender	|
    |Argentina |male  	|
    
@Smoke @LandingPage
Scenario Outline: Landing page default login with the name
    Given User is on General Store landing page
    When User choosing his country <country> from dropdown
    And and his Gender <gender> from checkboxes
    And entering his name <name> in Your name field
    And and clicking on Lets shop button
    Then Toast error message for entering the name is not displayed
    And User successfully getting to Products page
    
    Examples:
    |country	 |gender	|name    |
    |Argentina |male  	|Bob     |
    |Argentina |female  |Jessica |