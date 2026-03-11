package com.kns.restcountriesexplorerbe.dto;

import lombok.Data;

@Data
public class CountryDTO {
    private String name;
    private String capital;
    private String region;
    private long population;
    private String flag;
}
