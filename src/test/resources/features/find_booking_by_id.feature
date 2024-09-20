@scope
Feature: Find a booking using ID
  Scenario: find a booking by id
    Given  I have a booking system endpoint "/booking"
    When I get a booking using bookingid
    Then The system responds with status code 200
    And Validate the booking id
    And Validate the lastname