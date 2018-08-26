package com.test.testapplication.connector.impl;

import com.test.testapplication.connector.DescriptionConnector;
import com.test.testapplication.connector.dto.DataCountryDTO;
import com.test.testapplication.connector.dto.DataItems;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class DescriptionConnectorImpl implements DescriptionConnector {

    private String endpoint;


    public DescriptionConnectorImpl(@Value("${description.country}") String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Mono<DataCountryDTO> getDescriptionList(String codeCountry) {
        WebClient webClient = WebClient.create(endpoint);

        return  webClient.get()
                .uri("/country/"+codeCountry)
                .accept(APPLICATION_JSON)
                .exchange()
                .flatMap(y -> y.bodyToMono(DataCountryDTO.class));

//        DataCountryDTO data = new DataCountryDTO();
//        List<DataItems> dataCountry = new ArrayList<>();
//        DataItems dataItems = new DataItems();
//        dataItems.setDescription("population");
//        dataItems.setCode("0001");
//        dataCountry.add(dataItems);
//        data.setDataCountry(dataCountry);
//        return Mono.just(data);
    }
}
