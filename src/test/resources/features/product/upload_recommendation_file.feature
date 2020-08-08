Feature: Upload Products Recommendations File

  In order to make available product recommendation data
  As a Skava service
  I want to be able to consume it as necessary from the Facade Service


  Background:
    Given the environment ext5 is selected

  Scenario Outline: Upload product recommendation file
    When sending a POST request with a binary file <file name>
    Then the service return a response with file length <length> and rows inserted <rows>

    Examples:
      | file name | length  | rows   |
      | test1.csv | 218     | 5      |
    #  | test2.csv | 5899806 | 168424 |