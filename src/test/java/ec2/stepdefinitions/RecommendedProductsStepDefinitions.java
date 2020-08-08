package ec2.stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ec2.model.Product;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;

import java.util.List;

import static ec2.constants.Constants.*;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;


public class RecommendedProductsStepDefinitions {

    String url = "https://{{env}}skava-product-recommendations-service.yleo.us/api";


    @Given("^the environment (.*) is selected$")
    public void the_environment_is_selected(String env) {
        RestAssured.baseURI = url.replace("{{env}}", env);
    }


    @When("^(.*) requests the recommended part numbers list for (.*)$")
    public void requests_the_recommended_part_numbers_list_for(String actor, String partNumber) {

        Product product = new Product.ProductBuilder(partNumber)
                .build();

        SerenityRest
                .given()
                .header(AUTHORIZATION_TYPE, API_KEY_VALUE)
                .contentType(CONTENT_TYPE)
                .body(product)
                .when().post(GET_RECOMMENDATIONS_STRING)
                .then().statusCode(200)
                .and().body("success", is(true));
    }


    @Then("^s?he should see the part numbers (.*)$")
    public void you_should_see_the_part_numbers(List<String> recommendedList) {

        List<String> recommendedListResponse = lastResponse().jsonPath().getList("recommendationsResponse.recommendedPartNumbers");
        assertThat(recommendedList).containsAll(recommendedListResponse);
    }


    @When("^(.*) requests (\\d+) recommended part number for (.*)$")
    public void requests_recommended_part_number_for(String actor, int recommendationsToReturn, String partNumber) {

        Product product = new Product.ProductBuilder(partNumber)
                .recommendationsToReturn(recommendationsToReturn)
                .build();

        SerenityRest
                .given()
                .header(AUTHORIZATION_TYPE, API_KEY_VALUE)
                .contentType(CONTENT_TYPE)
                .body(product)
                .when().post(GET_RECOMMENDATIONS_STRING)
                .then().statusCode(200)
                .and().body("success", is(true));
    }


    @Then("^he should see a maximum of (\\d+) in the part number list$")
    public void he_should_see_a_maximum_of_in_the_part_number_list(int recommendationsToReturn) {

        int size = lastResponse().jsonPath().getInt("recommendationsResponse.recommendedPartNumbers.size()");
        assertThat(size).isEqualTo(recommendationsToReturn);
    }


    @When("^(.*) requests with invalid member$")
    public void requests_with_invalid_member(String actor) {
        String body = "{\n" +
                "\t\"PartNumbers\": []\n" +
                "}";

        SerenityRest
                .given()
                .header(AUTHORIZATION_TYPE, API_KEY_VALUE)
                .contentType(CONTENT_TYPE)
                .body(body)
                .when().post(GET_RECOMMENDATIONS_STRING)
                .then().statusCode(200)
                .and().body("success", is(false));
    }


    @Then("^he should see the error message: (.*)$")
    public void he_should_see_the_error_message(String errorMessage) {

        String message = lastResponse().jsonPath().getString("errorMessage");
        assertThat(message).containsSequence(errorMessage);
    }
}