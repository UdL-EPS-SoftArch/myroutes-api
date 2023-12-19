Feature: Retrieve RouteFollowed
  In order to get RouteFollowed
  I want to get a RouteFollowed

  Background:
    Given There is a registered user with username "user" and password "password" and email "user@sample.app"
    Given There is a route with a title "testRoute", description "route description", type "Running" and a creationDate "2023-10-25T17:27:00Z" by user username "user"
    Given There is a routeFollowed with date "2023-11-01T15:45:00Z", duration "P1DT1H10M10.5S", levelUp "40" and a levelDown "10", by user username "user" and route title "testRoute"


  Scenario: Get a RouteFollowed
    Given I login as "user" with password "password"
    When I try to retrieve a RouteFollowed with user "user" and route "testRoute"
    Then The response code is 200

