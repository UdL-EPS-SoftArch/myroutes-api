Feature: Create RouteVersion
  In order to use the app
  As a user
  I want to create RouteVersion and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"

  Scenario: Create new correct RouteVersion
    Given I login as "username" with password "password"
    When I create a new correct RouteVersion
    Then The response code is 201


