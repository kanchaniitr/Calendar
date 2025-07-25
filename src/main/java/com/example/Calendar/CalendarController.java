package com.example.Calendar;

import com.example.Calendar.dto.CountriesDTO;
import com.example.Calendar.dto.HolidaysDTO;
import com.example.Calendar.externalApi.HolidayWithName;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<HolidaysDTO> getHolidays(@PathParam("countryCode") String countryCode,
                                         @PathParam("startDate") String startDate,
                                         @PathParam("endDate") String endDate) {
        List<HolidaysDTO> holidays = new ArrayList<>();
        if (ObjectUtils.isEmpty(countryCode) || ObjectUtils.isEmpty(startDate) || ObjectUtils.isEmpty(endDate))
            return holidays;
        holidays = getHolidaysList(countryCode, startDate, endDate);
        return holidays;
    }

    @GetMapping("/countries")
    public List<CountriesDTO> getAllCountries() {
        return calendarService.getAllCountries().stream()
                .map(dto -> new CountriesDTO(dto.getName(), dto.getCode()))
                .collect(Collectors.toList());
    }

    public List<HolidaysDTO> getHolidaysList(String countryCode, String sDate, String eDate) {
        int year = Integer.parseInt(sDate.substring(0, 4));
        int sMonth = Integer.parseInt(sDate.substring(4, 6));
        int eMonth = Integer.parseInt(eDate.substring(4, 6));
        List<HolidaysDTO> toReturn = new ArrayList<>();
        List<HolidayWithName> holidays = calendarService.getHolidaysList(countryCode, year);
        for (HolidayWithName hDay : holidays) {
            int hMonth = hDay.getDate().getDatetime().getMonth();
            if (hMonth >= sMonth && hMonth <= eMonth)
                toReturn.add(new HolidaysDTO(hDay.getDate().toString(), hDay.getName()));
        }
        return toReturn;
    }
}
