@MoonCreation

Feature: Moon Creation

  Background:
    Given The user is in the log in page
    When The user can enter "test" and "test"
    And The user clicks the login button
    Then The user should be redirected to the Home page of their account
    Then The planet should exist within the celestial body list "1"
    When When The User sets the dropdown menu to Planet
    When The user selects Moon option
    Then The user should see two input fields

    ############################# POSITIVE Scenarios Using Planet ID  #######################################

  @UniqueMoonName
  Scenario Outline: As a user I should be able to create moon with a unique name that orbits an existing planet
    When The user enters a unique moon name "<Moon Name>"
    When The user enters their planet id "<Planet ID>"
    When The user hits the create moon button
    Then The user should see their new moon in the homepage "<Moon Name>"


    Examples:
    |username| password| Planet Name| Planet ID| Moon Name |
    | test | test      | test       | 1        |test       |


    @ZeroCharacterMoonName
    Scenario Outline: As as user I should be able to create a moon with a name that has 0 characters that orbits an existing planet
      When The user enters a unique moon name "<Moon Name>"
      When The user enters their planet id "<Planet ID>"
      When The user hits the create moon button
      Then The user should see their new moon in the homepage "<Moon Name>"


      Examples:
        |username| password| Planet Name| Planet ID| Moon Name |
        | test | test      | test       | 1        ||


  @30CharacterMoonName
  Scenario Outline: As as user I should be able to create a moon with a name that has 30 characters that orbits an existing planet
    When The user enters a unique moon name "<Moon Name>"
    When The user enters their planet id "<Planet ID>"
    When The user hits the create moon button
    Then The user should see their new moon in the homepage "<Moon Name>"


    Examples:
      |username| password| Planet Name| Planet ID| Moon Name |
      | test | test      | test       | 1        |STANSTANSTANSTANSTANSTANSTANST|

    ############################# Positive Scenarios Using Planet Name #######################################

  @UniqueMoonNamePlanetName
  Scenario Outline: As a user I should be able to create moon with a unique name that orbits an existing planet's name
    When The user enters a unique moon name "<Moon Name>"
    When The user enters their planet name "<Planet Name>"
    When The user hits the create moon button
    Then The user should see their new moon in the homepage "<Moon Name>"


    Examples:
      |username| password| Planet Name          | Planet ID| Moon Name          |
      | test | test      |testMoonCreationPlanet| 1        |testMoonCreationMoon|


    # No need for a zero character moon with planet name as its taken


  @30CharacterMoonNamePlanetName
  Scenario Outline: As as user I should be able to create a moon with a name that has 30 characters that orbits an existing planet's name
    When The user enters a unique moon name "<Moon Name>"
    When The user enters their planet name "<Planet Name>"
    When The user hits the create moon button
    Then The user should see their new moon in the homepage "<Moon Name>"


    Examples:
      |username| password| Planet Name          | Planet ID| Moon Name                    |
      | test | test      |testMoonCreationPlanet| 1        |RANDYRANDYRANDYRANDYRANDYRANDY|



     ############################# NEGATIVE Scenarios Using Planet ID #######################################

    @NonUniqueName
    Scenario Outline: As as user I should not be able to create a moon with a non-unique name that orbits an existing planet
      When The user enters a non-unique moon name "<Moon Name>"
      When The user enters their planet id "<Planet ID>"
      When The user hits the create moon button
      Then The user should not see two moons of the same name in the homepage "<Moon Name>"

      Examples:
        |username| password| Planet Name| Planet ID| Moon Name            |
        | test | test      | test       | 50       |testUniqueMoonNegative|

      @PlanetDoesNotExist
      Scenario Outline: As a user I should be not able to create moon with a unique name that orbits an non-existing planet
        When The user enters a unique moon name "<Moon Name>"
        When The user enters the non-existing planet id "<Planet ID>"
        When The user hits the create moon button
        Then The user should not see their new moon in the homepage "<Moon Name>"


        Examples:
          |username| password| Planet Name| Planet ID| Moon Name              |
          | test | test      | test       | 404      |testNoPlanetMoonNegative|




      @31CharacterMoonName
      Scenario Outline: As as user I should not be able to create a moon with a name that has 31 characters that orbits an existing planet
        When The user enters a unique long moon name "<Moon Name>"
        When The user enters their planet id "<Planet ID>"
        When The user hits the create moon button
        Then The user should not see their new moon in the homepage "<Moon Name>"


        Examples:
          |username| password| Planet Name| Planet ID| Moon Name |
          | test | test      | test       | 50       |KENNYKENNYKENNYKENNYKENNYKENNYK|

 ############################# Negative Scenarios Using Planet Name #######################################
  @NonUniqueNamePlanetName
  Scenario Outline: As as user I should not be able to create a moon with a non-unique name that orbits an existing planet's name
    When The user enters a non-unique moon name "<Moon Name>"
    When The user enters their planet name "<Planet Name>"
    When The user hits the create moon button
    Then The user should not see two moons of the same name in the homepage "<Moon Name>"

    Examples:
      |username| password| Planet Name          | Planet ID| Moon Name            |
      | test | test      |testMoonCreationPlanet| 1        |testUniqueMoonNegative|


  @PlanetDoesNotExistPlanetName
  Scenario Outline: As a user I should be not able to create moon with a unique name that orbits an non-existing planet's name
    When The user enters a unique moon name "<Moon Name>"
    When The user enters the non-existing planet name "<Planet Name>"
    When The user hits the create moon button
    Then The user should not see their new moon in the homepage "<Moon Name>"


    Examples:
      |username| password| Planet Name        | Planet ID| Moon Name                             |
      | test | test      | testNonExtantPlanet| 2        |testCreateMoonOnNonExtantPlanetNegative|




  @31CharacterMoonNamePlanetName
  Scenario Outline: As as user I should not be able to create a moon with a name that has 31 characters that orbits an existing planet's name
    When The user enters a unique long moon name "<Moon Name>"
    When The user enters their planet name "<Planet Name>"
    When The user hits the create moon button
    Then The user should not see their new moon in the homepage "<Moon Name>"


    Examples:
      |username| password| Planet Name          | Planet ID| Moon Name                     |
      | test | test      |testMoonCreationPlanet| 1        |CARTMANCARTMANCARTMANCARTMANCAR|

