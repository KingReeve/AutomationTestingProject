# Planetarium

The Planetarium is a web service that allows Astronomers (Users) to record planets and associated moons to a central database as they map the night sky. Users must register an account to participate, and those who do will be able to associate themselves with the planets and moons they add to the database. Much of the infrastructure has already been created and tested: your job is to create automated versions of the tests previously written and executed, and add new automated test for new test cases developed.

# Project Focus

### As the project focus is on testing, there will be several features that have been changed to be non-functional

- Java
- Selenium
- Cucumber
- Junit
- Jira
- Test Case Creation
- Test Reporting
- System Testing

# Key Terminology

- **Project**
  - A software application built for some company/entity
  - Examples
    - Employee paid time off Scheduler
    - Helicopter Navigation System
    - To-Do task manager
- **Sprint**
  - a term used to describe a short period of development work, typically no more than a few weeks of time
- **Minimum Viable Product**
  - a phrase used to describe a project that has the minimum number of features and functionality applied to make the sprint considered successful
- **Requirement**
  - a functional or conceptual need
    - functional examples
      - usernames should be between 5-15 characters
      - a user's online shopping cart should not allow for purchings a negative number of items
    - conceptual examples
      - users should be able to log in
      - users should be able to purchase items

## Business Requirements

- Users should be able to open a new account with the Planetarium
- Users should be able to securely log in to their account
- Users should be able to add new Planets to the Planetarium
- Users should be able to remove Planets from the Planetarium they previously added
- Users should be able to add Moons to the Planetarium associated with their Planets
- Users should be able to remove Moons from the Planetarium they previously added
- Users should be able to view Planet and Moons they have added to the Planetarium

## Software Requirements  

- Users should have unique usernames
- Usernames and passwords should not be longer than 30 characters
- Users should only see Planet and Moon data for resources they have added to the Planetarium
- Planet and Moon names should not have more than 30 characters
- Planets and moons should have unique names
- Planets should be “owned” by the user that added it to the Planetarium
- Moons should be “owned” by the Planet the User adding the moon associated it with

## Development Requirements

Each class in the list below has one or more unimplemented methods that should be implemented in order to achieve MVP requirements:

- UserDao
- PlanetDao
- MoonDao
- UserService
- PlanetService
- MoonService

## System Testing Requirements

- All Test Reporting should be done in Jira
- All Software and Business requirements require a minimum of one positive test
- All Software and Business requirements require a minimum of one negative test
- All Software and Business requirements with Requirements that limit data **input** require Boundary Analysis Testing
- All Software and Business requirements with Requirements that limit data **visibility** require Exploratory Testing to verify Business Requirements are met
- All Software and Business requirements with Requirements that limit data **interaction** require Exploratory Testing to verify Business Requirements are met
- All tests that fail should be logged in a Defect Report inside of Jira

### MVP Requirements

- all provided methods in the "repository" package are implemented to meet business and software requirements
- all provided methods in the "service" package are implemented to meet business and software requirements
- all System Testing Requirements are tested
- all testing is automated with Java using Selenium, Cucumber, and Junit
- all reporting is stored in Jira
