package com.example.Calendar;

import com.example.Calendar.dto.CountriesDTO;
import com.example.Calendar.dto.HolidaysDTO;
import com.example.Calendar.externalApi.CountriesResponse;
import com.example.Calendar.externalApi.CountryNameWithCode;
import com.example.Calendar.externalApi.HolidayResponse;
import com.example.Calendar.externalApi.HolidayWithName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private static final String baseURI = "https://calendarific.com/api/v2";
    private static final String API_KEY = "NdF0PJ78ygeyjqTX7k318lZFSkYWlCGZ";
    private static final String countriesURI = baseURI + "/countries?api_key=" + API_KEY;
    private static final String holidaysURI = baseURI + "/holidays?api_key=" + API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    private List<CountryNameWithCode> fetchCountriesList() {
        CountriesResponse countryResponse = restTemplate.getForObject(countriesURI, CountriesResponse.class);
        if (!ObjectUtils.isEmpty(countryResponse)) {
            return countryResponse.getResponse().getCountries();
        }
        return Collections.emptyList();
    }

    private List<HolidayWithName> fetchHolidayList(String countryCode, int year) {
        String url = String.format("%s&country=%s&year=%d", holidaysURI, countryCode, year);
        HolidayResponse holidays = restTemplate.getForObject(url, HolidayResponse.class);

        return holidays.getResponse().getHolidays();
    }

    public List<CountriesDTO> getAllCountries() {
        List<CountryNameWithCode> countriesList = fetchCountriesList();
        return countriesList.stream()
                .map(dto -> new CountriesDTO(dto.getName(), dto.getCode()))
                .collect(Collectors.toList());
    }

    public List<HolidaysDTO> getHolidaysList(String countryCode, String sDate, String eDate) {
        int year = Integer.parseInt(sDate.substring(0, 4));
        int sMonth = Integer.parseInt(sDate.substring(4, 6));
        int eMonth = Integer.parseInt(eDate.substring(4, 6));
        List<HolidaysDTO> toReturn = new ArrayList<>();
        List<HolidayWithName> holidays = fetchHolidayList(countryCode, year);
        for (HolidayWithName hDay : holidays) {
            int hMonth = hDay.getDate().getDatetime().getMonth();
            if (hMonth >= sMonth && hMonth <= eMonth)
                toReturn.add(new HolidaysDTO(hDay.getDate().toString(), hDay.getName()));
        }
        return toReturn;
    }
}
