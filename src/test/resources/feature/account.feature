Feature: Create and Get a Account

  Scenario: When a customer passes an valid customer id and request to create account
    Given Customer provides a valid customer id "1001"
    When The customer makes a call to create an account
    Then The API should return the account and customer details
