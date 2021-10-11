Feature: Check tour received from API

  Scenario: receiving tours with parameters from API
    When get list with tours
    Then Check all tours have right price 700
    Then Check all tours have right cityOut
      | Москва |
    Then Check all tours have right dates
      | 2021-10-19 |
      | 2021-10-31 |

  Scenario: get right parameters for receiving tours from API
    When get catalog of parameters
    Then Check status code 200
    Then Check response contains id Almaty 2707

  Scenario: get country guide
    When get country guide
    Then Check status code with RestAssured 201
