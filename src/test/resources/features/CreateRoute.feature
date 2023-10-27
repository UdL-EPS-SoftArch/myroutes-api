Feature: Route
  In order to use the app
  As a user
  I must be able to CRUD Routes

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given I login as "user" with password "password"

  Scenario: Create a route without being logged in
    Given I'm not logged in
    When I create a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Create a route while logged in
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 201

  Scenario: Create an empty route while logged in
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "", description "", type "" and a creationDate ""
    Then The response code is 400
    And The error message is "must not be empty"

  Scenario: Create a route while logged in without title
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 400
    And The error message is "must not be empty"

  Scenario: Create a route while logged in without description
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "testRoute", description "", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 201

  Scenario: Create a route while logged in without type
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "testRoute", description "route description", type "" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 201

  Scenario: Create a route while logged in with unexistent type
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "testRoute", description "route description", type "Swimming" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 201

  Scenario: Create a route while logged in without creationDate
    Given I login as "user" with password "password"
    And I don't have any route
    When I create a route with a title "testRoute", description "route description", type "Running" and a creationDate ""
    Then The response code is 201
