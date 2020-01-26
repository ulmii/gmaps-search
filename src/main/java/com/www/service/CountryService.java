package com.www.service;

import com.www.model.Country;
import com.www.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService
{
    private CountryRepository repository;

    public Iterable<Country> list()
    {
        return repository.findAll();
    }

    public Optional<Country> load(String id)
    {
        return repository.findById(id);
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
