Feature: Retrieve Waypoint
  In order to get Waypoint
  I want to get a Waypoint

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"

  Scenario: Get a Waypoint
    Given I login as "username" with password "password"
    When I try to retrieve a Waypoint with title "Sample Waypoint"
    Then The response code is 200

  Scenario: Get a Waypoint which does not exist
    Given I login as "username" with password "password"
    When I try to retrieve a Waypoint with title "Nothing here"
    Then The response code is 404