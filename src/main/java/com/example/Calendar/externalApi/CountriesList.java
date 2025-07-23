package com.example.Calendar.externalApi;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class CountriesList {
    String url;
    private List<CountryNameWithCode> countries;

    public List<CountryNameWithCode> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryNameWithCode> countries) {
        this.countries = countries;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
