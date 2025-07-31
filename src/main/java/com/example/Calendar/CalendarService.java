package com.example.Calendar;

import com.example.Calendar.dto.CountriesDTO;
import com.example.Calendar.dto.HolidayNameDTO;
import com.example.Calendar.dto.HolidaysDTO;
import com.example.Calendar.dto.StartOfWeekDayDTO;
import com.example.Calendar.externalApi.CountriesResponse;
import com.example.Calendar.externalApi.CountryNameWithCode;
import com.example.Calendar.externalApi.HolidayResponse;
import com.example.Calendar.externalApi.HolidayWithName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CalendarService {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter viewDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String baseURI = "https://calendarific.com/api/v2";
    private static final String API_KEY = "NdF0PJ78ygeyjqTX7k318lZFSkYWlCGZ";
    private static final String countriesURI = baseURI + "/countries?api_key=" + API_KEY;
    private static final String holidaysURI = baseURI + "/holidays?api_key=" + API_KEY;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WikiService wikiService;

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

    public List<StartOfWeekDayDTO> getHolidaysList(String country, String countryCode, String sDate, String eDate) {
        int year = Integer.parseInt(sDate.substring(0, 4));
        List<HolidayWithName> holidays = fetchHolidayList(countryCode, year);
        return getHolidaysWithinRange(countryCode, holidays, sDate, eDate);
    }

    private List<StartOfWeekDayDTO> getHolidaysWithinRange(String country, List<HolidayWithName> holidays, String sDate, String eDate) {
        LocalDate startDate = LocalDate.parse(sDate, dateFormatter);
        LocalDate endDate = LocalDate.parse(eDate, dateFormatter);
        Map<LocalDate, StartOfWeekDayDTO> weeklyHolidays = new LinkedHashMap<>();
        for (HolidayWithName holiday : holidays) {
            String dateStr = holiday.getDate().getDatetime().toString();
            LocalDate curDate = LocalDate.parse(dateStr, dateFormatter);
            if (!curDate.isBefore(startDate) && !curDate.isAfter(endDate) && !isWeekend(curDate)) {
                LocalDate firstDayOfWeek = getFistDayOfWeek(curDate);
                weeklyHolidays.computeIfAbsent(firstDayOfWeek,
                        l -> new StartOfWeekDayDTO(firstDayOfWeek.format(dateFormatter)));
                String holidayName = holiday.getName();
                String wikiToken = wikiService.getWikiToken(country, holidayName);
                HolidayNameDTO holidayNameDTO = new HolidayNameDTO(holidayName, wikiToken);
                weeklyHolidays.get(firstDayOfWeek)
                        .addHoliday(curDate, new HolidaysDTO(viewDateFormatter.format(curDate), holidayNameDTO));
            }
        }
        return weeklyHolidays.values().stream().toList();
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    private LocalDate getFistDayOfWeek(LocalDate date) {
        return date.minusDays(date.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue() + 1);
    }
}
