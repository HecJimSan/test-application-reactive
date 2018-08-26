package com.test.testapplication.bdd;


import com.test.testapplication.controller.dto.InfoDetailsDTO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.nio.ByteBuffer;
import java.util.function.Function;


public class InformationSteps extends CucumberRoot {


    public static final String CODE_MESSAGE = "code should contains ES for the country selected";
    public static final String DESCRIPTION_MESSAGE = "description should contains Nice place to have visit";
    private  String countrySelection;
    private String descriptionSelection;
    private InfoDetailsDTO infoDetails;
    private Function<UriBuilder, URI> uriBuilderURIFunction;
    private WebTestClient.ResponseSpec exchange;

    @Given("^a client wants to get relevant data about ([^\\\\s]+) and ([^\\\\s]+)")
    public void a_client_wants_to_get_relevant_data_about(String country, String description) throws Throwable {
        countrySelection = country;
        descriptionSelection = description;
        uriBuilderURIFunction = uri -> uri
                .path("/information")
                .queryParam("country", country)
                .queryParam("description", description)
                .build();
    }

    @When("^it makes a request to get information")
    public void it_makes_a_request_to_get_information_about_Spain() throws Throwable {
        exchange = testClient.get()
                .uri(uriBuilderURIFunction)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();



//        infoDetails = testRestTemplate.getForObject(stringBuilder.toString(), InfoDetailsDTO.class);
    }

    @Then("^the client receives the data$")
    public void the_client_receives_the_data() throws Throwable {
        byte[] responseBody = exchange
                .expectStatus().is2xxSuccessful()
                .expectBody().returnResult().getResponseBody();
        ByteBuffer bb = ByteBuffer.wrap(responseBody);
        String response = new String(responseBody);

        Assert.assertEquals(CODE_MESSAGE, infoDetails.getCode(), "BE");
        Assert.assertEquals(DESCRIPTION_MESSAGE, infoDetails.getDescription(), "any");
    }



}