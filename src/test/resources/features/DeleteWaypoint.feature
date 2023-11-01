Feature: Delete Waypoint
  In order to delete Waypoint
  I want to delete a Waypoint

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"

  Scenario: Delete a Waypoint with user logged in
    Given I login as "username" with password "password"
    When I delete the Waypoint with title "Sample Waypoint"
    Then The response code is 204

  Scenario: Delete a Waypoint with user not logged in
    Given I'm not logged in
    When I delete the Waypoint with title "Sample Waypoint"
    Then The response code is 401

  Scenario: Delete a Waypoint that doesn't exist
    Given I login as "username" with password "password"
    When I delete the Waypoint with title "Nothing here"
    Then The response code is 404