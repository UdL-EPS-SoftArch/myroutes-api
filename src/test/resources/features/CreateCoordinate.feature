Feature: Create Coordinate
  In order to use the app
  As a user
  I want to create coordinate and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Create new coordinate
    When I create a new coordinate with value "coordinate"
    Then The response code is 401
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "error coordinate"
    Then The error message is "Invalid format for Coordinate"

