@scope
Feature: Create a new booking

  Scenario Outline: new valid booking
    Given I have a booking system endpoint "/booking"
    When I enter the following data
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And I post the data to the system
    Then The system responds with status code 200
    And the system displayes the booking ID

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin    | checkout   | additionalneeds |
      | Veli      | Zuma     | 2500       | true           | 2024-09-17 | 2024-09-22 | Breakfast       |
