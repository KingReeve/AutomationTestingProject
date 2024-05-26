@DeleteMoonFeature
  Feature: A User wants to delete a Moon

  Background:
    Given The user goes to the log in page
    When The user can enters "test1" and "test1"
    And The user selects the login button
    Then The user should be redirected to the Home page for their account
    When When The User sets the dropdown menu to Planet
    When The user selects Moon option
    Then The user should see two input fields


@PositiveDeleteMoonByID
  Scenario Outline: A user wants to delete a Moon by ID
    When The User enters a "<MoonID>" in the Delete Field
    When The User selects the Delete Button
    Then Moon with ID "<MoonID>" is Deleted from the Database

  Examples:
    | MoonID |
    |5      |

@NegativeDeleteOtherUsersMoon
Scenario Outline: A user wants to Delete a Moon they do not have access to
  When The User enters a "<MoonID>" in the Delete Field that belongs to another User
  When The User selects the Delete Button
  Then Moon with ID "<MoonID>" is not Deleted from the Database

Examples:
  |MoonID |
  |4         |