package com.kns.restcountriesexplorerbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestCountriesExplorerBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestCountriesExplorerBeApplication.class, args);
    }

}
