@MoonSearch

  Feature: Moon Search

    Background:
      Given The user is in the log in page

    @PositiveMoonNameSearch
    Scenario Outline: A user should be able to search for their moons by name.
      When The user can enter "<username>" and "<password>"
      And The user clicks the login button
      Then The user should be redirected to the Home page of their account
      When The user inputs "<Moon Query>"
      When The user clicks the Moon Search button.
      Then The user should see only the moon "<Moon Query>"

      Examples:
      |username|password|Moon Query|
      |test1   |test1   |MoonSearchPositive|

    @NegativeMoonNameSearch
    Scenario Outline: A user should not be able to search for moons they don't own by name.
      When The user can enter "<username>" and "<password>"
      And The user clicks the login button
      Then The user should be redirected to the Home page of their account
      When The user inputs "<Moon Query>"
      When The user clicks the Moon Search button.
      Then The user should see nothing

      Examples:
      |username|password|Moon Query        |
      |test1   |test1   |MoonSearchNegative|

    @PositiveMoonIDSearch
    Scenario Outline: A user should be able to search for their moons by ID.
      When The user can enter "<username>" and "<password>"
      And The user clicks the login button
      Then The user should be redirected to the Home page of their account
      When The user inputs "<Moon Query>"
      When The user clicks the Moon Search button.
      Then The user should see only the moon "<Moon Query>"

      Examples:
        |username|password|Moon Query|
        |test1   |test1   |60        |

    @NegativeMoonIDSearch
    Scenario Outline: A user should not be able to search for moons they don't own by ID.
      When The user can enter "<username>" and "<password>"
      And The user clicks the login button
      Then The user should be redirected to the Home page of their account
      When The user inputs "<Moon Query>"
      When The user clicks the Moon Search button.
      Then The user should see nothing

      Examples:
        |username|password|Moon Query|
        |test1   |test1   |61        |