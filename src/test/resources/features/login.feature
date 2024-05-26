@PositiveLogin
Feature: User should be able to log into a account

    Background:
      Given The user is in the log in page

      Scenario Outline: User can input their username and password to log into their account
        When The user can enter "<username>" and "<password>"
        And The user clicks the login button
        Then The user should be redirected to the Home page of their account

      Examples:
        |username  | password |
        | test     | test     |
        | test1    | test1    |