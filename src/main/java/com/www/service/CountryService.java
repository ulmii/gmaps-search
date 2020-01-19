package com.www.service;

import com.www.model.Country;
import com.www.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryService
{
    private CountryRepository repository;

    public Iterable<Country> list()
    {
        return repository.findAll();
    }

    public Country save(Country country)
    {
        return repository.save(country);
    }

    public void save(List<Country> country)
    {
        repository.saveAll(country);
    }
}
