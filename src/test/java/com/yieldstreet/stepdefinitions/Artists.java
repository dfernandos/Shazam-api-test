package com.yieldstreet.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class Artists {

    public RequestSpecification httpRequest;
    public Response response;

    public ResponseBody responseBody;
    private static final String BASE_URL = "https://shazam.p.rapidapi.com/";
    private static final String ENDPOINT = "artists/get-top-songs";


    @Given("I hit the url for get top artists api endpoint")
    public void i_hit_the_url_for_get_top_artists_api_endpoint() {
        RestAssured.baseURI = BASE_URL;
    }

    @Then("I receive the response code as 200")
    public void i_receive_the_response_code_as_200() {
        int responseCode = response.statusCode();
        Assertions.assertEquals(responseCode, 200);
    }


    @Then("I verify that the type of the first object is {}")
    public void iVerifyThatTheTypeOfTheFirstObjectIsFirstObjectType(String type) {
        responseBody = response.getBody();
        JsonPath jsonPath = response.jsonPath();
        String typeToBeValidated = jsonPath.getJsonObject("data.type[0]").toString();

        Assertions.assertEquals(typeToBeValidated, type);
    }

    @Then("I verify that the artist name is {}")
    public void iVerifyThatTheArtistNameIsArtistName(String artistName) {

        responseBody = response.getBody();
        JsonPath jsonPath = response.jsonPath();
        List<String> artistNames = jsonPath.getList("data.attributes.artistName");

        for (String artistNameActual : artistNames) {
            Assertions.assertEquals(artistName, artistNameActual,
                    "Artist name mismatch: expected " + artistName + " but got " + artistNameActual);
        }
    }

    @Then("I verify the total of the songs are equal to 10")
    public void iVerifyTheTotalOfTheSongsAreEqualTo() {
        responseBody = response.getBody();
        JsonPath jsonPath = response.jsonPath();
        List<String> songs = jsonPath.getList("data");
        Assertions.assertEquals(songs.size(), 10);
    }

    @Then("I receive the response code as 404")
    public void iReceiveTheResponseCodeAs() {
        System.out.println(response.getBody().prettyPrint());
        JsonPath jsonPath = response.jsonPath();
        String errorStatus = jsonPath.getJsonObject("errors.status[0]").toString();
        Assertions.assertEquals(errorStatus, "404");
    }

    @When("I pass the url in the request for the artist {}")
    public void iPassTheUrlInTheRequestOrTheArtistArtistId(String id) {
        this.httpRequest = RestAssured.given()
                .header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c6cfae4770mshfa1364e50bc1a23p11c49bjsn88f8e97d46c0")
                .queryParam("id", id)
                .queryParam("l", "en-US");
        this.response = httpRequest.get(ENDPOINT);
    }

    @When("I pass the url in the request with an invalid API key")
    public void iPassTheUrlInTheRequestWithAnInvalidAPIKey() {
        this.httpRequest = RestAssured.given()
                .header("X-RapidAPI-Host", "shazam.p.rapidapi.com")
                .header("X-RapidAPI-Key", "c6cfae4770mshfa1364e50bc1a23p11c49bjsn88f8e97d46c05454545")
                .queryParam("id", "453453")
                .queryParam("l", "en-US");
        this.response = httpRequest.get(ENDPOINT);
    }

    @Then("I receive an error response code")
    public void iReceiveAnErrorResponseCode() {
        int responseCode = response.statusCode();
        Assertions.assertEquals(responseCode, 403);
    }
}
