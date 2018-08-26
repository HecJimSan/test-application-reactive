package com.test.testapplication.service.impl;

import com.test.testapplication.connector.DescriptionConnector;
import com.test.testapplication.controller.dto.InfoDetailsDTO;
import com.test.testapplication.repository.CodeRepository;
import com.test.testapplication.service.InformationService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class InformationServiceImpl implements InformationService {

    private CodeRepository codeRepository;
    private DescriptionConnector descriptionConnector;

    public InformationServiceImpl(CodeRepository codeRepository, DescriptionConnector descriptionConnector) {
        this.codeRepository = codeRepository;
        this.descriptionConnector = descriptionConnector;
    }

    @Override
    public Mono<InfoDetailsDTO> getDetails(String country, String description) {
        String code = codeRepository.getCode(country);

        return descriptionConnector.getDescriptionList(code)
                .map(x -> {
                            Optional<InfoDetailsDTO> infoDetailsDTO1 = x.getDataCountry()
                                    .stream()
                                    .filter(z -> z.getDescription().equalsIgnoreCase(description))
                                    .findFirst()
                                    .map(y -> {
                                        InfoDetailsDTO infoDetailsDTO = new InfoDetailsDTO();
                                        infoDetailsDTO.setDescription(y.getDescription());
                                        infoDetailsDTO.setCode(code);
                                        return infoDetailsDTO;
                                    });
                            return infoDetailsDTO1
                                    .get();
                        }
                );
    }
}
