@NegativeLogin
Feature: User should not be able to log into a account

  Background:
    Given The user is in the log in page

  Scenario Outline: User can not input their username and password to login into their account
    When The user can input "<username>" and "<password>"
    And The user clicks the login button on the login page
    Then The user should be moved to the Home page of their account

    Examples:
      |username       | password    |
      |test           | notPassword |
      |notValidName   | test        |