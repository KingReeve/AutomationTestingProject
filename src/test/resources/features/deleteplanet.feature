@DeletePlanetFeature
  Feature: A User wants to delete a Planet

  Background:
    Given The user goes to the log in page
    When The user can enters "test1" and "test1"
    And The user selects the login button
    Then The user should be redirected to the Home page for their account


@PositiveDeletePlanetByID
  Scenario Outline: A user wants to delete a Planet by ID
    When The User sets the dropdown menu to Planet.
    When The User enters a "<PlanetID>" in the Delete Field
    When The User selects the Delete Button
    Then Planet with ID "<PlanetID>" is Deleted from the Database

  Examples:
    | PlanetID |
    |9         |

@PositiveDeletePlanetByName
  Scenario Outline: A user wants to delete a Planet by Name
    When The User sets the dropdown menu to Planet.
    When The User enters a "<PlanetName>" in the Delete Field
    When The User selects the Delete Button
    Then Planet with Name "<PlanetName>" is Deleted from the Database

  Examples:
    | PlanetName |
    |testPlanetDeleteNamePositive|

@NegativeDeleteOtherUsersPlanet
  Scenario Outline: A user wants to Delete a Planet they do not have access to
    When The User sets the dropdown menu to Planet.
    When The User enters a "<PlanetID>" in the Delete Field that belongs to another User
    When The User selects the Delete Button
    Then Planet with ID "<PlanetID>" is not Deleted from the Database

  Examples:
    | PlanetID |
    |7         |