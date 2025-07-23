package com.example.Calendar.externalApi;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CountriesResponse {

    CountriesList response;

    public CountriesList getResponse() {
        return response;
    }

    public void setResponse(CountriesList response) {
        this.response = response;
    }
}
