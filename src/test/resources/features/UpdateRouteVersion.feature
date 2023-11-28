Feature: Update RouteVersion
  In order to use the app
  As a user
  I want to update RouteVersion and check throw errors

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@domain.com"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z" by user username "username"
    Given There is a route version of a route with title "testRoute"

  Scenario: Update RouteVersion using PATCH
    Given I login as "username" with password "password"
    When I update a creationDate RouteVersion whit a Route title "testRoute" using PATCH
    Then The response code is 403

  Scenario: Update RouteVersion using PUT
    Given I login as "username" with password "password"
    When I update a creationDate RouteVersion whit a Route title "testRoute" using PUT
    Then The response code is 403

  Scenario: Update a RouteVersion not logged in using PUT
    Given I'm not logged in
    When I update a creationDate RouteVersion whit a Route title "testRoute" using PUT
    Then The response code is 401

  Scenario: Update a RouteVersion not logged in using PATCH
    Given I'm not logged in
    When I update a creationDate RouteVersion whit a Route title "testRoute" using PATCH
    Then The response code is 401