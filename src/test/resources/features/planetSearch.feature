@PlanetSearch
  Feature: Planet Search

    Background:
      Given The user is in the log in page
      When The user can enter "test1" and "test1"
      And The user clicks the login button
      Then The user should be redirected to the Home page of their account

      @PositivePlanetSearchByName
      Scenario Outline: A user should be able to search a planet by name
        When The users enters the name of "<Planet Name>"
        Then The users should be able to view the Planet in the homepage "<Planet Name>"
        Examples:
          |Planet Name             |
          |testPlanetSearchPositive|

      @NegativePlanetSearchByName
      Scenario Outline: A user should not be able to search a planet they do not own
        When The user enters "<Planet Name>"
        Then The user should see no change in the view of the homepage "<Planet Name>"
        Examples:
          | Planet Name            |
          |testPlanetSearchNegative|

      @PositivePlanetSearchById
      Scenario Outline: A user should be able to search for a planet by id
        When The user enters the id of "<Planet Id>"
        Then The user should be able to view the Planet in the homepage "<Planet Id>"
        Examples:
          |Planet Id  |
          | 1         |

    @NegativePlanetSearchById
    Scenario Outline: A user should not be able to search for a planet by id they do not own
      When The users enters the id of "<Planet Id>"
      Then The users should not see any changes to the homepage "<Planet Id>"
      Examples:
        |Planet Id  |
        | 72        |