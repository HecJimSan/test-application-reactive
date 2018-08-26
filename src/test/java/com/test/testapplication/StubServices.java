package com.test.testapplication;


import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.test.testapplication.connector.dto.DataCountryDTO;
import org.junit.Assert;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.client.HttpClientOptions;

import java.util.function.Consumer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

public class StubServices {

    @Autowired
    private RestTemplate restTemplate;


    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig()
            .port(3005));


    public void stubCountryDescriptionServiceForCode1235(){
        configureFor("localhost", 3005);
        givenThat(get(urlEqualTo("/country/fiji"))
                .willReturn(aResponse()
                        .withBodyFile("response-description.json")
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));




//        Mono<DataCountryDTO> mono = webClient.get()
//                .uri("/country/1235")
//                .accept(APPLICATION_JSON)
//                .exchange()
//                .flatMap(x -> x.bodyToMono(DataCountryDTO.class));
//        mono.subscribe(x->{
//            Assert.assertNotNull(x);
//        });


//        WebClient webClient = WebClient.builder().baseUrl("http://localhost:3005")
//                .clientConnector(new ReactorClientHttpConnector(builder -> builder.disablePool()))
//                .build();
        WebClient webClient = WebClient.create("http://localhost:3005");

        Mono<DataCountryDTO> dataCountryDTOMono = webClient.get().uri("/country/fiji").accept(APPLICATION_JSON)
                .retrieve().bodyToMono(DataCountryDTO.class);


        DataCountryDTO dataCountryDTO = dataCountryDTOMono.block();
        Assert.assertEquals(dataCountryDTO.getDataCountry().size(), 6);
    }

    public void resetService(){
        wireMockRule.stop();
    }

    public void startService(){
        wireMockRule.start();
    }


}
