Feature: Delete RouteVersion
  In order to use the app
  As a user
  I want to delete RouteVersion and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z" by user username "username"
    Given There is a route version of a route with title "testRoute"

  Scenario: Delete RouteVersion
    Given I login as "username" with password "password"
    When I delete a RouteVersion whit a Route title "testRoute"
    Then The response code is 200
    Then The number of versions for the route with the title "testRoute" is 0

  Scenario: Delete a RouteVersion not logged in
    Given I'm not logged in
    When I delete a RouteVersion whit a Route title "testRoute"
    Then The response code is 401