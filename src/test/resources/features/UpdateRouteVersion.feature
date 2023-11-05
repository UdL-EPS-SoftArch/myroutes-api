Feature: Create RouteVersion
  In order to use the app
  As a user
  I want to create RouteVersion and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z" by user username "username"
    Given I create a new correct RouteVersion whit a Route title "testRoute"

  Scenario: Update RouteVersion
    Given I login as "username" with password "password"
    When I update a creationDate RouteVersion whit a Route title "testRoute"
    Then The response code is 201

