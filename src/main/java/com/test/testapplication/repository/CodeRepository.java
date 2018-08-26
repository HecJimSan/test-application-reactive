package com.test.testapplication.repository;


import com.test.testapplication.repository.dto.CountryDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CodeRepository {
    Mono<String> getCode(String country);
}
