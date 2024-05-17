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

  Scenario: Verify a 404 status code is returned for an invalid artist
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request for the artist 567072999
    Then I receive the response code as 404

  Scenario: Verify a 403 status code is returned for an invalid API key
    Given I hit the url for get top artists api endpoint
    When I pass the url in the request with an invalid API key
    Then I receive an error response code