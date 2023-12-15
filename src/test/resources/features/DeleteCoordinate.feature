Feature: Delete Coordinate
  In order to use the app
  As a user
  I want to delete coordinate and check throw errors

  Background:
    Given There is a registered admin user with username "username" and password "password" and email "user@domain.com"
    And I create a new valid Coordinate

  Scenario: Delete coordinate
    Given I login as "username" with password "password"
    When I delete that Coordinate
    Then The response code is 200

  Scenario: Delete coordinate not exist
    Given I login as "username" with password "password"
    When I delete unknown Coordinate
    Then The response code is 404
