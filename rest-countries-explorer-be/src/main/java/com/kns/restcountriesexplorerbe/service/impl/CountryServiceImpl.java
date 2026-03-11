package com.kns.restcountriesexplorerbe.service.impl;

import com.kns.restcountriesexplorerbe.dto.CountryDTO;
import com.kns.restcountriesexplorerbe.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {
    private List<CountryDTO> cachedCountries = new ArrayList<>();
    private final RestTemplate restTemplate;

    public CountryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CountryDTO> getAllCountries() {
        return cachedCountries;
    }

    @Override
    public List<CountryDTO> searchCountries(String query) {
        return cachedCountries.stream()
                .filter(c -> c.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Scheduled(fixedRate = 600000)
    @Override
    public void fetchAndCacheCountries() {
        String url =  "https://restcountries.com/v3.1/all?fields=name,capital,region,population,flags";

        try {
            List<Map<String, Object>> response = restTemplate.getForObject(url, List.class);

            if (response != null) {
                this.cachedCountries = response.stream().map(data -> {
                    CountryDTO dto = new CountryDTO();

                    // Name mapping
                    Map<String, Object> nameObj = (Map<String, Object>) data.get("name");
                    if (nameObj != null) {
                        dto.setName((String) nameObj.get("common"));
                    }

                    // Capital mapping
                    List<String> capitals = (List<String>) data.get("capital");
                    dto.setCapital(capitals != null && !capitals.isEmpty() ? capitals.get(0) : "N/A");

                    // Region mapping
                    dto.setRegion((String) data.get("region"));

                    // Population mapping
                    if (data.get("population") != null) {
                        dto.setPopulation(((Number) data.get("population")).longValue());
                    }

                    // Flag mapping
                    Map<String, String> flagsObj = (Map<String, String>) data.get("flags");
                    if (flagsObj != null) {
                        dto.setFlag(flagsObj.get("png"));
                    }

                    return dto;
                }).collect(Collectors.toList());

                System.out.println("Countries cache updated successfully at: " + java.time.LocalDateTime.now());
            }
        } catch (Exception e) {
            System.err.println("Error fetching countries: " + e.getMessage());
        }
    }

    @PostConstruct
    public void init() {
        fetchAndCacheCountries();
    }
}