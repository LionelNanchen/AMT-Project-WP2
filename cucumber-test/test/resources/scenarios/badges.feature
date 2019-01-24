Feature: Badges

  Scenario: Get all badges
    Given I have an api key
    When I try to POST a new badge
    Then I receive status code <200>
    When I GET all the badges from the endpoint /badges
    Then I receive status code <200>
    When I try to DELETE the badge
    Then I receive status code <200>

  Scenario: Create a new badge
    Given I have an api key
    When I try to POST a new badge
    Then I receive status code <200>
    When I try to DELETE the badge
    Then I receive status code <200>

  Scenario: Delete a badge
    Given I have an api key
    When I try to POST a new badge
    Then I receive status code <200>
    When I try to DELETE the badge
    Then I receive status code <200>

  Scenario: Modify a badge
    Given I have an api key
    When I try to POST a new badge
    Then I receive status code <200>
    When I try to change the badge's name
    Then I receive status code <200>
    When I try to DELETE the badge
    Then I receive status code <200>
