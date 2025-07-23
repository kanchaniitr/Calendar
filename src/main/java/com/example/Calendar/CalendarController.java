package com.example.Calendar;

import com.example.Calendar.dto.MonthCalendarDTO;
import com.example.Calendar.dto.QuarterOfYear;
import com.example.Calendar.externalApi.CountryNameWithCode;
import com.example.Calendar.externalApi.HolidayWithName;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.*;

@RestController
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @RequestMapping("/")
    public String index() {
        return "index.js";
    }

    @GetMapping("/calendar/month")
    public MonthCalendarDTO getMonthCalendar(@PathParam("country") String country,
                                             @PathParam("month") Month month,
                                             @PathParam("year") int year) {
        MonthCalendarDTO monthCalendarDTO = new MonthCalendarDTO(year, month);
        Map<Integer, String> holidayMap = getHolidayMap(country, year, month);
        monthCalendarDTO.updateHoliday(holidayMap);
        return monthCalendarDTO;
    }

    @GetMapping("/calendar/quarter")
    public List<MonthCalendarDTO> getQuarterCalendar(@PathParam("country") String country,
                                                     @PathParam("quarter") QuarterOfYear quarter,
                                                     @PathParam("year") int year) {
        List<MonthCalendarDTO> quarterCalenderDTO = new ArrayList<>();
        for (Month month : quarter.getMonthList()) {
            MonthCalendarDTO monthCalendarDTO = new MonthCalendarDTO(year, month);
            Map<Integer, String> holidayMap = getHolidayMap(country, year, month);
            monthCalendarDTO.updateHoliday(holidayMap);
            quarterCalenderDTO.add(monthCalendarDTO);
        }
        return quarterCalenderDTO;
    }

    @GetMapping("/countries")
    public List<CountryNameWithCode> getAllCountries() {
        return calendarService.getAllCountries();
    }

    @GetMapping("/months")
    public List<Month> getAllMonths() {
        return Arrays.asList(Month.values());
    }

    @GetMapping("/quarters")
    public List<QuarterOfYear> getAllQuarters() {
        return Arrays.asList(QuarterOfYear.values());
    }

    public Map<Integer, String> getHolidayMap(String countryCode, int year, Month month) {
        List<HolidayWithName> holidays = calendarService.getHolidaysList(countryCode, year, month);
        Map<Integer, String> holidayMap = new HashMap<>();
        holidays.forEach(holidayWithName -> {
            holidayMap.put(holidayWithName.getDate().getDatetime().getDay(), holidayWithName.getName());
        });
        return holidayMap;
    }
}
