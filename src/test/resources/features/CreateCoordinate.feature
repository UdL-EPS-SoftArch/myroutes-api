Feature: Create Coordinate
  In order to use the app
  As a user
  I want to create coordinate and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Create new incorrect coordinate
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "error coordinate"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Create new incorrect coordinate upper latitude 90
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "90.00000,179.99999"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Create new incorrect coordinate upper longitude 180
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "89.99999,180.00000"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Create new incorrect coordinate lower latitude -90
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "-90.00000,-179.99999"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Create new incorrect coordinate lower longitude -180
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "-89.99999,-180.00000"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Create new correct coordinate upper latitude and longitude
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "89.99999,179.99999"
    Then The response code is 201

  Scenario: Create new correct lower latitude and longitude
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "-89.99999,-179.99999"
    Then The response code is 201

  Scenario: Create new correct random latitude and longitude
    Given I login as "username" with password "password"
    When I create a new Coordinate with value "41.40338,2.17403"
    Then The response code is 201
