package ec2.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

import static ec2.constants.Constants.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UploadRecommendationFileStepDefinitions {

    String url = "https://{{env}}productrecommendation.yleo.us/api";

    @When("^sending a POST request with a binary file (.*)$")
    public void sending_a_POST_request_with_a_binary_file(String fileName) {

        File testUpLoadFile = new File(FILE_CSV_PATH + fileName);
        SerenityRest
                .given()
                .header(AUTHORIZATION_TYPE, API_KEY_VALUE)
                .multiPart(testUpLoadFile)
                .when().post(IMPORT_STRING);
                //.then().statusCode(200);
    }

    @Then("^the service return a response with file length (\\d+) and rows inserted (\\d+)$")
    public void the_service_return_a_response_with_file_length_and_rows_inserted(int size, int rows) {

     //  String expectedMessage = "File received [length: " + Integer.toString(size) + " ; rows inserted: " + Integer.toString(rows) + "]";
     //  assertThat(lastResponse().asString()).containsSequence("File received");
     //  restAssuredThat(lastResponse -> lastResponse.body(equalTo(expectedMessage)));
    }


}