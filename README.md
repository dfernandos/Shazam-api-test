# Shazam api test

The purpose of this assessment is to test a public api. It was choose the Shazam api to be tested. 

## Prerequisites

1. Java 12 or latest
2. Maven
3. Junit 4
4. RestAssured
5. Cucumber

## Installation

Once all prerequisites are installed you will need to:

- Clone this repository

```bash
git clone git@github.com:dfernandos/Shazam-api-test.git
```

## Run tests

In order to run the test you will need to run the following command:

```bash
mvn test
```

## Project structure



```
src/test/java
    ├── com
    │   └── yieldstreet
    │       ├── features (In this directory you will find the feature file with the BDD scenarios)
    │       │   └── GetTopArtists.feature
    │       ├── stepdefinitions (Here you will find the test scenarios implamented)
    │       │   └── Artists.java
    │       └── runner (Here you will fin the test runner)
    │           └── RunCucumberTest.java
```

## Justification for choosing these scenarios

The objective of the tested endpoint is to get the top 10 songs of a specific artist. The scenarios mapped will ensure that the main objective of the endpoint are functioning. Based on this objective, it was decided to test the following scenarios:

```
Feature: Get Top Songs by artists from Shazam API

  Scenario: Verify the get top songs
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request for the artist 567072
    Then I receive the response code as 200

  Scenario Outline: Verify the type attribute of the first object is returning as "Song"
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request for the artist 567072
    Then I verify that the type of the first object is <FirstObjectType>
    Examples:
      | FirstObjectType |
      | songs           |

  Scenario Outline: Verify the artist name is Gorillaz
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request for the artist 567072
    Then I verify that the artist name is <ArtistName>
    Examples:
      | ArtistName |
      | Gorillaz   |

  Scenario: Verify the total of the songs are equal to 10
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request for the artist 567072
    Then I verify the total of the songs are equal to 10

  Scenario: Verify a 404 status code is returned for an in valid artist
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request for the artist 567072999
    Then I receive the response code as 404

  Scenario: Verify a 403 status code is returned for an invalid API key
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request with an invalid API key
    Then I receive an error response code
```

## Continuous Integration

Every commit in this project is running a git action workflow that builds the maven artifact and run all tests. 

## Reporting

In order to generate the test report you will need to run the following command:

```bash
mvn clean verify 
```