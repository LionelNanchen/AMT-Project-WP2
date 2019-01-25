Feature: Events

  Scenario: Post an event
    Given I have an api key
    And I have a badge
    And I have a pointScale
    And I have a rule
    When I try to POST a new event
    Then I receive status code <201>
