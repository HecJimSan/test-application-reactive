package com.test.testapplication.controller;

import com.test.testapplication.StubServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class InformationControllerIT extends StubServices {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void handleRequest() throws Exception {
        stubCountryDescriptionServiceForCode1235();
//         testClient.get()
//                .uri(uri -> uri
//                        .path("/information")
//                        .queryParam("country", "fiji")
//                        .queryParam("description", "population")
//                        .build()
//                )
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus().is2xxSuccessful().expectBody().json("{\"hello\":\"string\"}");
    }
}