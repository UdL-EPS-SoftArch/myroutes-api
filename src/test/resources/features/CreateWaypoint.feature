Feature: Create Waypoint
  In order to manage waypoints
  As a user
  I want to create a new waypoint and validate the data

  Scenario: Create a Waypoint with valid data
    Given I can login with username "username" and password "password"
    When I create a new Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"
    Then The response code is 201
    And There is 1 Waypoint created
    And I try to retrieve that Waypoint
    And The response code is 200

  Scenario: Create a Waypoint with missing title
    Given I can login with username "username" and password "password"
    When I create a new Waypoint with title "" description "A test waypoint" and type "Sample"
    Then The response code is 400
    And There is 0 Waypoint created

  Scenario: Create a Waypoint with missing description
    Given I can login with username "username" and password "password"
    When I create a new Waypoint with title "Sample Waypoint" description "" and type "Sample"
    Then The response code is 400
    And There is 0 Waypoint created

  Scenario: Create a Waypoint with missing type
    Given I can login with username "username" and password "password"
    When I create a new Waypoint with title "Sample Waypoint" description "A test waypoint" and type ""
    Then The response code is 400
    And There is 0 Waypoint created

  Scenario: Create a Waypoint with missing title and description and type
    Given I can login with username "username" and password "password"
    When I create a new Waypoint with title "" description "" and type ""
    Then The response code is 400
    And There is 0 Waypoint created

  Scenario: Create a Waypoint that already exists
    Given I can login with username "username" and password "password"
    When I create a new Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"
    And The response code is 201
    And I create a new Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"
    Then The response code is 409
    And There is 1 Waypoint created

  Scenario: Create a new Waypoint when I am not logged in
    Given I'm not logged in
    When I create a new Waypoint with title "Sample Waypoint" description "A test waypoint" and type "Sample"
    Then The response code is 401
    And There is 0 Waypoint created
