package com.example.Calendar;

import com.example.Calendar.externalApi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Month;
import java.util.Collections;
import java.util.List;

@Service
public class CalendarService {

    private static final String baseURI = "https://calendarific.com/api/v2";
    private static final String API_KEY = "NdF0PJ78ygeyjqTX7k318lZFSkYWlCGZ";
    private static final String countriesURI = baseURI + "/countries?api_key=" + API_KEY;
    private static final String holidaysURI = baseURI + "/holidays?api_key=" + API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    public List<CountryNameWithCode> getAllCountries() {
        CountriesResponse countryResponse = restTemplate.getForObject(countriesURI, CountriesResponse.class);
        if(!ObjectUtils.isEmpty(countryResponse)){
            return countryResponse.getResponse().getCountries();
        }
        return Collections.emptyList();
    }

    public List<HolidayWithName> getHolidaysList(String countryCode, int year) {
        String url = String.format("%s&country=%s&year=%d", holidaysURI, countryCode, year);
        HolidayResponse holidays = restTemplate.getForObject(url, HolidayResponse.class);

        return holidays.getResponse().getHolidays();
    }
}
