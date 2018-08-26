package com.test.testapplication.connector;


import com.test.testapplication.connector.dto.DataCountryDTO;
import reactor.core.publisher.Mono;

public interface DescriptionConnector {
    Mono<DataCountryDTO> getDescriptionList(String codeCountry);
}
