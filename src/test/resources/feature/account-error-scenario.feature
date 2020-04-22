Feature: Error Scenarios

  Scenario: When a non existing customer id is passed
    Given Customer provides a non existing customer id "2000"
    When The customer makes a call to create an account
    Then The API should return error "Account cannot be created, Customer not found" and status code 404

  Scenario: When a long number is passed
    Given Customer provides a invalid customer id "100000000000000"
    When The customer makes a call to create an account
    Then The API should return error "Bad Request, invalid customer id" and status code 400
