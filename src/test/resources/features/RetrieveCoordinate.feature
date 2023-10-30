Feature: Retrieve Coordinate
  In order to use the app
  As a user
  I want to retrieve coordinate and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Retrieve coordinate
    Given I login as "username" with password "password"
    And I create a new valid Coordinate
    When I retrieve that Coordinate
    Then The response code is 200

  Scenario: Retrieve a Coordinate which does not exist
    Given I login as "username" with password "password"
    When I try to retrieve a Coordinate with id 23535
    Then The response code is 404
