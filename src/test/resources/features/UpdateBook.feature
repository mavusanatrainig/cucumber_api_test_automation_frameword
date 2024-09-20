@scope
Feature: Update booking

  Scenario Outline: update a booking
    Given I have a booking system endpoint "/booking"
    When I enter the following data
      | firstname   | lastname   | totalprice   | depositpaid   | checkin   | checkout   | additionalneeds   |
      | <firstname> | <lastname> | <totalprice> | <depositpaid> | <checkin> | <checkout> | <additionalneeds> |
    And I post the data to the system
    Then The system responds with status code 200
    And the system displayes the booking ID

    Examples:
      | firstname | lastname | totalprice | depositpaid | checkin | checkout | additionalneeds |
      | History   | Zulu     | -          | true        | -       | -        | -               |
