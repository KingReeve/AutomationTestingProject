@RegistrationNegative
Feature: User should be unable to register an account

  Background:
    Given The user is in the log in page
    When The user clicks the Create an Account link
    Then The user should be redirected to the Create An Account Page

    Scenario Outline: User can not input invalid data for username and passwords to create an account
      When The user enters invalid "<username>" and "<password>"
      When The user clicks upon create account button
      Then The user should not receive an alert stating account creation successful
      Then The user should not be redirected to the log in page

    Examples:
    | username | password |
    |test1     |test1     |
    |1234567890123456789012345678901 | 1 |
    |1 | 1234567890123456789012345678901 |