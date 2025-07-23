package com.example.Calendar.dto;

import com.example.Calendar.util.DateUtil;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.*;

@Component
@NoArgsConstructor
public class MonthCalendarDTO {
    private int year;
    private String month;
    private List<DateDTO> dates;
    private Map<Integer, String> holidays;


    public MonthCalendarDTO(int year, Month month) {
        this.year = year;
        this.month = month.name();
        this.dates = new ArrayList<>();
        this.holidays = new HashMap<>();
        dates.addAll(DateUtil.getDateDTO(year, month));
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public List<DateDTO> getDates() {
        return dates;
    }

    public void updateHoliday(Map<Integer, String> holidayMap) {
        this.holidays.putAll(holidayMap);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (DateDTO dateDTO : dates) {
            if (holidayMap.keySet().contains(dateDTO.getDate()) && !dateDTO.isWeekend()) {
                map.computeIfAbsent(dateDTO.getStartOfWeek(), k -> new ArrayList<>()).add(dateDTO.getDate());
                dateDTO.setHoliday(true);
            }
        }

        for (Map.Entry<Integer, List<Integer>> en : map.entrySet()) {
            if (en.getValue().size() == 1) {
                this.fillColor(en.getKey(), DateFillColor.LightGreen);
            } else
                this.fillColor(en.getKey(), DateFillColor.DarkGreen);
        }
    }

    private void fillColor(int startOfWeek, DateFillColor color) {
        for (int i = startOfWeek - 1; i < startOfWeek + 4; i++) {
            dates.get(i).setFillColor(color);
        }
    }
}
