Feature: Create Coordinate
  In order to use the app
  As a user
  I want to create coordinate

  Scenario: Create new coordinate
    When I create a new coordinate with value "coordinate"
    Then The response code is 401
