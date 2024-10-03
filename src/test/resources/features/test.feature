@test
Feature: Test
  Scenario Outline: Test
    Given I am
    When I get data
      | firstname   | lastname  | mobileNumber   | homeNumber   | address_line1   | city   | province   | postalCode   |
      | <firstname> | <lastname> | <mobileNumber> | <homeNumber> | <address_line1> | <city> | <province> | <postalCode> |
    Then I move





  Examples:
    | firstname | lastname          | mobileNumber | homeNumber | address_line1 | city         | province | postalCode || Vusi      | Pelo      | 083737615    | 10111      | 44564 Junha   | Johannesburg | Gauteng  | 2200       |