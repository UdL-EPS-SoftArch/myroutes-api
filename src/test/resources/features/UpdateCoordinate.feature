Feature: Update Coordinate
  In order to use the app
  As a user
  I want to update coordinate and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"
    And I create a new valid Coordinate

  Scenario: Update coordinate
    Given I login as "username" with password "password"
    When I update that Coordinate with new value "-89.99999,-179.99999"
    Then The response code is 200
