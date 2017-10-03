Feature: As​ a client​ I would​ like​ to​ add​ products​ to​ the​ shopping​ basket.

Background:
	Given I open 'https://www.zalando.com'
    And I want to start shoping using 'Zalando.UK'

Scenario: As​ a client​ I would​ like​ to​ add two​ products​ to​ the​ shopping​ basket.    
    Given I click on 'Kids' wear
    And I search for the word 'babies'
    And I want to make the following filters on search results:
    |Gender:Girls|Age:Babies|Colour:Black|Size:3K|Material:Textile|Brand:adidas Originals|
    And I click on item number 1
    And I select '3K' size
    And I add the item to shopping basket
    And I click back
    And I click on item number 3
    And I select '3K' size
    And I add the item to shopping basket
    When I open the shopping basket
    Then I should see 2 items in the shopping basket