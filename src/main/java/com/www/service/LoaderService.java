package com.www.service;

import com.www.configuration.CountryConfiguration;
import com.www.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;

@Service
public class LoaderService
{
    private static final Logger log = LoggerFactory.getLogger(LoaderService.class);

    private CountryConfiguration configuration;

    @Autowired
    public LoaderService(CountryConfiguration configuration)
    {
        this.configuration = configuration;
    }

    @HystrixCommand(
            fallbackMethod = "getFromFile",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") })
    public List<Country> getFromUrl()
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Country>> rateResponse =
                restTemplate.exchange("https://restcountries.eu/rest/v2/all",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>()
                        {
                        });

        log.info("Countries saved from url");
        return rateResponse.getBody();
    }

    public List<Country> getFromFile()
    {
        log.warn("GET from remote failed, reading json from file");
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>()
        {
        };

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json"))
        {
            List<Country> countries = mapper.readValue(inputStream, typeReference);
            log.info("Countries saved from json file");

            return countries;

        }
        catch (Exception e)
        {
            log.error("Unable to read from json file: " + e.getMessage());
        }
        log.info("Falling back to data from configuration");

        return configuration.getCountries();
    }
}
