
@RegistrationPositive
Feature: User should be able to register an account

  Background:
    Given The user is in the log in page
    When The user clicks the Create an Account link
    Then The user should be redirected to the Create An Account Page

    Scenario Outline: User can input a username and password to create an account
      When The user enters "<username>" and "<password>"
      When The user clicks on create account button
      Then The user should receive an alert stating account creation successful
      Then The user should be redirected to the log in page

    Examples:
    | username | password |
    | Kevin1   | Geer1    |
    | 123456789012345678901234567890 | 1 |
    | 1 | 123456789012345678901234567890 |