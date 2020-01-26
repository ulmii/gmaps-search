package com.www.controller;

import com.www.model.Country;
import com.www.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class CountryResource
{
    private CountryService service;

    @Autowired
    public CountryResource(CountryService service)
    {
        this.service = service;
    }

    @GetMapping(value = "/autocomplete")
    public List<String> autocomplete(@RequestParam(value = "country", defaultValue = "") final String country)
    {
        return StreamSupport.stream(service.list().spliterator(), false)
                .filter(s -> s.getName().toLowerCase().startsWith(country.toLowerCase()))
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/countries")
    public List<Country> getCountries(@RequestParam(value = "country", defaultValue = "") final String country)
    {
        return StreamSupport.stream(service.list().spliterator(), false)
                .filter(s -> s.getName().toLowerCase().startsWith(country.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/country/{id}")
    public Optional<Country> getCountry(@PathVariable String id)
    {
        return service.load(id);
    }
}
