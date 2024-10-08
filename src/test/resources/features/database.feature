@data
  Feature: Test using DB
    Scenario Outline: TestDB
      Given I have an apple
      When I bite it using the data
        |product_id|
        |<product_id>|


      Examples:
        |product_id|
        |1|
        |2|
        |3|
        |4|
        |5|
        |6|
        |7|
        |8|
        |9|
        |10|