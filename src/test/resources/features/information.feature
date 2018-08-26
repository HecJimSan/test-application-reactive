Feature: Provide different scenarios

  Scenario: Get information based in one identifier
    Given a client wants to get relevant data about Spain and population
    When it makes a request to get information
    Then the client receives the data