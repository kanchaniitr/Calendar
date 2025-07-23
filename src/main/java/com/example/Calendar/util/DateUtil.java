package com.example.Calendar.util;

import com.example.Calendar.dto.DateDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Component
public class DateUtil {
    public static List<DateDTO> getDateDTO(int year, Month month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        int noOfDays = endOfMonth.getDayOfMonth();
        List<DateDTO> dates = new ArrayList<>();
        for(int i = noOfDays - 1; i > 0; i--) {
            dates.add(new DateDTO(endOfMonth.minusDays(i)));
        }

        return dates;
    }
}
