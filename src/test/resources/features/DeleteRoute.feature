Feature: Delete Route
  In order to use the app
  As a user
  I must be able to delete Routes

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login as "user" with password "password"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"

  Scenario: Delete a Route not logged in
    Given I'm not logged in
    When I try to delete Route with title "testRoute"
    Then The response code is 401

  Scenario: Delete a Route as user
    Given I login as "user" with password "password"
    When I try to delete Route with title "testRoute"
    Then The response code is 403

    ##make a post instead of delete?
  Scenario: Delete a Route as admin
    Given There is a registered admin with name "admin" and password "password" and email "admin@sample.app"
    Given I login as "admin" with password "password"
    When I try to delete Route with title "testRoute"
    Then The response code is 200

  Scenario: Delete a Route as reviewer
    Given I login as "reviewer" with password "password"
    When I try to delete Route with title "testRoutes"
    Then The response code is 401

  Scenario: Delete a Route that does not exist as user
    Given I login as "user" with password "password"
    When I try to delete Route with title "testRoutes"
    Then The response code is 403

  Scenario: Delete a Route that does not exist as admin
    Given I login as "admin" with password "password"
    When I try to delete Route with title "testRoutes"
    Then The response code is 401

  Scenario: Delete a Route that does not exist as reviewer
    Given I login as "reviewer" with password "password"
    When I try to delete Route with title "testRoutes"
    Then The response code is 401

