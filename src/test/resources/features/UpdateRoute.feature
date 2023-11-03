Feature: Update Route
  In order to use the app
  As a user
  I must be able to update Routes

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z" by user username "user"

  Scenario: Update a route without being logged in
    Given I'm not logged in
    When I update a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 401
    And The error message is "Unauthorized"

  Scenario: Update a route while logged in as user
    Given I login as "user" with password "password"
    When I update a route with a title "testRouteUpdated", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 200
    Then This route is as expected to be title "testRouteUpdated"

  Scenario: Update a route while logged in as admin
    Given I login as "admin" with password "password"
    When I update a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 401

  Scenario: Update a route while logged in as reviewer
    Given I login as "reviewer" with password "password"
    When I update a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 401

  Scenario: Update an empty route while logged in
    Given I login as "user" with password "password"
    When I update a route with a title "", description "", type "" and a creationDate ""
    Then The response code is 400

  Scenario: Update a route while logged in without title
    Given I login as "user" with password "password"
    When I update a route with a title "", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 400
    And The error message is "must not be empty"

  Scenario: Update a route while logged in without description
    Given I login as "user" with password "password"
    When I update a route with a title "testRouteUpdated", description "", type "Running" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 200
    Then This route is as expected to be title "testRouteUpdated"

  Scenario: Update a route while logged in without type
    Given I login as "user" with password "password"
    When I update a route with a title "testRoute", description "route description", type "" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 400

  Scenario: Update a route while logged in with unexistent type
    Given I login as "user" with password "password"
    When I update a route with a title "testRouteUpdated", description "route description", type "Swimming" and a creationDate "2023-10-25T17:27:00Z"
    Then The response code is 200
    Then This route is as expected to be title "testRouteUpdated"

  Scenario: Update a route while logged in without creationDate
    Given I login as "user" with password "password"
    When I update a route with a title "testRouteUpdated", description "route description", type "Running" and a creationDate ""
    Then The response code is 200
    Then This route is as expected to be title "testRouteUpdated"
