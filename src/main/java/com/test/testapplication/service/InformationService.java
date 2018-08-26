package com.test.testapplication.service;

import com.test.testapplication.controller.dto.InfoDetailsDTO;
import reactor.core.publisher.Mono;


public interface InformationService {
    Mono<InfoDetailsDTO> getDetails(String country, String description);
}
