@PlanetCreation
  Feature: Planet Creation

    Background:
      Given The user is in the log in page
      When The user can enter "test1" and "test1"
      And The user clicks the login button
      Then The user should be redirected to the Home page of their account
      When When The User sets the dropdown menu to Planet

      @PositivePlanetCreation
      Scenario Outline: A user should be able to add a planet given a unique name
        Given There is no planet with name "<Planet Name>"
        When The user enters a "<Planet Name>"
        When The user clicks the submit button
        Then The user should see their new planet in the homepage "<Planet Name>"

        Examples:
        |Planet Name|
        |testPlanetCreationPositive |

    @PositivePlanetCreationBoundaryValue
    Scenario Outline: A user should be able to add a planet with 30 characters
      Given There is no planet with name "<Planet Name>"
      When The user enters a "<Planet Name>"
      When The user clicks the submit button
      Then The user should see their new planet in the homepage "<Planet Name>"

      Examples:
        |Planet Name|
        |123456789012345678901234567890 |

      @NonuniquePlanetName
      Scenario Outline: A user should not be able to add a planet given a name that already exists
        When The user enters a "<Planet Name>"
        When The user clicks the submit button
        Then The user should not see a second copy of their planet in the homepage "<Planet Name>"

        Examples:
          |Planet Name|
          |testPCreateUniqueNegative |

      @TooLongPlanetName
      Scenario Outline: A user should not be able to add a planet given a name that is longer than 30 character
        When The user enters a "<Planet Name>"
        When The user clicks the submit button
        Then The user should not see "<Planet Name>" in the homepage

        Examples:
          |Planet Name|
          |1234567890123456789012345678901 |