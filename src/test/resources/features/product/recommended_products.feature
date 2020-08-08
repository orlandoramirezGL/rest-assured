Feature: Recommended Products

  In order to make available product recommendation data
  As a Skava service
  I want to be able to consume it as necessary from the Facade Service


  Background:
    Given the environment env11 is selected

  Scenario Outline: Get recommended part numbers with mandatory parameter
    When Skava requests the recommended part numbers list for <part number>
    Then he should see the part numbers <recommend list>

    Examples:
      | part number | recommend list                                              |
      #  env11
      | 3422        | 3573, 25397, 3623, 3056, 3321, 5227, 5626, 3390, 5145, 3282 |
      | 3578        | 3575, 3614, 3602, 3423, 3074, 3560, 3356, 3399, 3318, 3509  |
      # ext5
      #| 3422        | 3573, 25397, 3056, 3321, 5227, 5626, 3282, 5145, 3557, 5591 |


  Scenario Outline: Get recommended part numbers with optional parameter
    When Skava requests <recommendations to return> recommended part number for <part number>
    Then he should see the part numbers <recommend list>
    And he should see a maximum of <recommendations to return> in the part number list

    Examples:
      | part number | recommendations to return | recommend list                                             |
      | 3423        | 0                         | 3614, 3575, 3578, 3399, 3405, 3356, 3743, 3602, 3389, 3409 |


  Scenario: Get recommended part numbers with invalid member
    When Skava requests with invalid member
    Then he should see the error message: Please, provide a valid part number.


