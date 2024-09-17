Feature: Find a booking using ID
  Scenario: find a booking by id
    Given  I have a booking system endpoint "/booking"
    When I get a booking using id
    Then The system reponds with a status code 200
    And Validate the booking id
    And Validate that the lastname