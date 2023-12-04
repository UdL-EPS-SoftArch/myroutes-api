Feature: Update RouteFollowed
  In order to use the app
  As a user
  I must be able to update Routes

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z" by user username "user"
    Given There is a routeFollowed with date "2023-11-01T15:45:00Z", duration "P1DT1H10M10.5S", levelUp "40" and a levelDown "10", by user username "user" and route title "testRoute"

    Scenario: Update RouteFollowed without being logged in
      Given I'm not logged in
      When I update a routeFollowed with date "2023-11-16T12:43:00Z", duration "P1DT1H10M10.5S", levelUp "60" and a levelDown "20", by user username "user" and route title "testRoute"
      Then The response code is 401
      And The error message is "Unauthorized"

  Scenario: Update RouteFollowed being logged in
    Given I login as "user" with password "password"
    When I update a routeFollowed with date "2023-11-16T12:43:00Z", duration "P1DT1H10M10.5S", levelUp "60" and a levelDown "20", by user username "user" and route title "testRoute"
    Then The response code is 200

  Scenario: Update RouteFollowed being logged in without date
    Given I login as "user" with password "password"
    When I update a routeFollowed with date "", duration "P1DT1H10M10.5S", levelUp "60" and a levelDown "20", by user username "user" and route title "testRoute"
    Then The response code is 200

  Scenario: Update RouteFollowed being logged in without duration
    Given I login as "user" with password "password"
    When I update a routeFollowed with date "2023-11-16T12:43:00Z", duration "", levelUp "60" and a levelDown "20", by user username "user" and route title "testRoute"
    Then The response code is 200

  Scenario: Update RouteFollowed being logged in without levelUp
    Given I login as "user" with password "password"
    When I update a routeFollowed with date "2023-11-16T12:43:00Z", duration "P1DT1H10M10.5S", levelUp "" and a levelDown "20", by user username "user" and route title "testRoute"
    Then The response code is 400

  Scenario: Update RouteFollowed being logged in without levelDown
    Given I login as "user" with password "password"
    When I update a routeFollowed with date "2023-11-16T12:43:00Z", duration "P1DT1H10M10.5S", levelUp "60" and a levelDown "", by user username "user" and route title "testRoute"
    Then The response code is 400
