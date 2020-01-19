package com.www.configuration;

import com.www.model.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "database")
@JsonIgnoreProperties({ "$$beanFactory" })
public class CountryConfiguration
{
    private List<Country> countries;
}
