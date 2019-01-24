Feature: Events

  Scenario: Get all events
    Given I have an api key
    When I try to POST a new event
    Then I receive status code <201>
    When I GET all the badges from the endpoint /events
    Then I receive status code <200>
    When I try to DELETE the event
    Then I receive status code <204>

  Scenario: Create a new event
    Given I have an api key
    When I try to POST a new event
    Then I receive status code <201>
    When I try to DELETE the event
    Then I receive status code <204>

  Scenario: Delete a event
    Given I have an api key
    When I try to POST a new event
    Then I receive status code <201>
    When I try to DELETE the event
    Then I receive status code <204>

  Scenario: Modify a event
    Given I have an api key
    When I try to POST a new event
    Then I receive status code <201>
    When I try to change the event's name
    Then I receive status code <200>
    When I try to DELETE the event
    Then I receive status code <204>
