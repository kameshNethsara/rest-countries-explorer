package com.kns.restcountriesexplorerbe.controller;

import com.kns.restcountriesexplorerbe.dto.CountryDTO;
import com.kns.restcountriesexplorerbe.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "*")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDTO>> getCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CountryDTO>> search(@RequestParam String name) {
        return ResponseEntity.ok(countryService.searchCountries(name));
    }
}
