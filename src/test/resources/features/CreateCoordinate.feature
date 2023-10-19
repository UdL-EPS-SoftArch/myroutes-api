Feature: Create Coordinate
  In order to use the app
  As a user
  I want to create coordinate and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Create new coordinate
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "error coordinate"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Wrong upper latitude 90
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "90.00000,179.99999"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Wrong upper longitude 180
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "89.99999,180.00000"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Wrong lower latitude -90
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "-90.00000,-179.99999"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Wrong lower longitude -180
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "-89.99999,-180.00000"
    Then The error message is "Invalid format for Coordinate"

  Scenario: Valid upper latitude and longitude
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "89.99999,179.99999"
    Then The response code is 201

  Scenario: Valid lower latitude and longitude
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "-89.99999,-179.99999"
    Then The response code is 201

  Scenario: Valid random latitude and longitude
    Given I can login with username "username" and password "password"
    When I create a new Coordinate with value "41.40338,2.17403"
    Then The response code is 201
