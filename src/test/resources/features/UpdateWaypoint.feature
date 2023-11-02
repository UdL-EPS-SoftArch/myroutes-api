Feature: Update Waypoint
  In order to update Waypoint
  I want to update a new Waypoint

  Background:
    Given There is a registered user with username "username" and password "password" and email "user@sample.app"
    And There is already a Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"

  Scenario: Update a Waypoint
    Given I login as "username" with password "password"
    When I update Waypoint with title "Sample Waypoint" by changing it to "Another Waypoint" and description to "Another description"
    Then The response code is 200

  Scenario: Update a Waypoint with invalid content
    Given I login as "username" with password "password"
    When I update Waypoint with title "Sample Waypoint" by changing it to ""
    Then The response code is 400

  Scenario: Update a Waypoint when I am not logged in
    Given I'm not logged in
    When I update Waypoint with title "Sample Waypoint" by changing it to "Sample Waypoint" and description to "A test waypoint"
    Then The response code is 401

  Scenario: Update a Waypoint that does not exist
    Given I login as "username" with password "password"
    When I update Waypoint with title "Nothing here" by changing it to "Sample Waypoint" and description to "A test waypoint"
    Then The response code is 404