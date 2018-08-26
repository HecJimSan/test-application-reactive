package com.test.testapplication.controller;

import com.test.testapplication.controller.dto.InfoDetailsDTO;
import com.test.testapplication.service.InformationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Component
public class InformationController {

    private final InformationService informationService;

    public InformationController( InformationService informationService) {
        this.informationService = informationService;
    }

    public Mono<ServerResponse> get(ServerRequest request) {

        String country = getParam(request, "country");
        String description = getParam(request, "description");

        Mono<InfoDetailsDTO> infoDetailsDTOMono = informationService.getDetails(country, description);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(infoDetailsDTOMono, InfoDetailsDTO.class));
    }

    private String getParam(ServerRequest request, String nameParam) {
        return Optional
                .ofNullable(request.queryParam(nameParam))
                .orElseThrow(() -> new IllegalArgumentException("not country found")).get();
    }
}
