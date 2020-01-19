package com.www.repository;

import com.www.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String>
{
}
