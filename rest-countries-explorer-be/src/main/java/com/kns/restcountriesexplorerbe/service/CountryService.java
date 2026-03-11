package com.kns.restcountriesexplorerbe.service;

import com.kns.restcountriesexplorerbe.dto.CountryDTO;

import java.util.List;

public interface CountryService {
    List<CountryDTO> getAllCountries();
    List<CountryDTO> searchCountries(String query);
    void fetchAndCacheCountries();
}