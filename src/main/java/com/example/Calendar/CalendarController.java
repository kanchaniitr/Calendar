package com.example.Calendar;

import com.example.Calendar.dto.CountriesDTO;
import com.example.Calendar.dto.StartOfWeekDayDTO;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @RequestMapping("/")
    public String index() {
        return "index.js";
    }

    @GetMapping("/holidays")
    public List<StartOfWeekDayDTO> getHolidays(@PathParam("countryCode") String country,
                                               @PathParam("countryCode") String countryCode,
                                               @PathParam("startDate") String startDate,
                                               @PathParam("endDate") String endDate) {
        List<StartOfWeekDayDTO> holidays = new ArrayList<>();
        if (ObjectUtils.isEmpty(countryCode) || ObjectUtils.isEmpty(startDate) || ObjectUtils.isEmpty(endDate))
            return holidays;
        holidays = calendarService.getHolidaysList(country, countryCode, startDate, endDate);
        return holidays;
    }

    @GetMapping("/countries")
    public List<CountriesDTO> getAllCountries() {
        return calendarService.getAllCountries();
    }
}
