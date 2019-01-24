Feature: Rules

  Scenario: Get all rules
    Given I have an api key
    When I try to POST a new rule
    Then I receive status code <201>
    When I GET all the badges from the endpoint /rules
    Then I receive status code <200>
    When I try to DELETE the rule
    Then I receive status code <204>

  Scenario: Create a new rule
    Given I have an api key
    When I try to POST a new rule
    Then I receive status code <201>
    When I try to DELETE the rule
    Then I receive status code <204>

  Scenario: Delete a rule
    Given I have an api key
    When I try to POST a new rule
    Then I receive status code <201>
    When I try to DELETE the rule
    Then I receive status code <204>

  Scenario: Modify a rule
    Given I have an api key
    When I try to POST a new rule
    Then I receive status code <201>
    When I try to change the rule's name
    Then I receive status code <200>
    When I try to DELETE the rule
    Then I receive status code <204>
