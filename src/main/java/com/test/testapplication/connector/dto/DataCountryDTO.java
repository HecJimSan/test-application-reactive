package com.test.testapplication.connector.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DataCountryDTO {

    private List<DataItems> dataCountry = new ArrayList<DataItems>();
}
