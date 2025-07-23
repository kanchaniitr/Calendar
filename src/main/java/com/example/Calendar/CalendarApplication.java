package com.example.Calendar;

import com.example.Calendar.externalApi.CountryNameWithCode;
import com.example.Calendar.externalApi.HolidayWithName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Month;
import java.util.List;

@SpringBootApplication
public class CalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendarApplication.class, args);
		CalendarService service = new CalendarService();

		List<CountryNameWithCode> countries = service.getAllCountries();
		System.out.println(countries);

		List<HolidayWithName> holidays = service.getHolidaysList("IN", 2025, Month.OCTOBER);
		System.out.println(holidays);
	}

}
