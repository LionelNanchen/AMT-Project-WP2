Feature: PointScales

  Scenario: Get all pointScales
    Given I have an api key
    When I try to POST a new pointScale
    Then I receive status code <200>
    When I GET all the badges from the endpoint /pointScale
    Then I receive status code <200>
    When I try to DELETE the pointScale
    Then I receive status code <200>

  Scenario: Create a new pointScale
    Given I have an api key
    When I try to POST a new pointScale
    Then I receive status code <200>
    When I try to DELETE the pointScale
    Then I receive status code <200>

  Scenario: Delete a pointScale
    Given I have an api key
    When I try to POST a new pointScale
    Then I receive status code <200>
    When I try to DELETE the pointScale
    Then I receive status code <200>

  Scenario: Modify a pointScale
    Given I have an api key
    When I try to POST a new pointScale
    Then I receive status code <200>
    When I try to change the pointScale's name
    Then I receive status code <200>
    When I try to DELETE the pointScale
    Then I receive status code <200>
