package com.example.Calendar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class StartOfWeekDayDTO {
    @Getter
    private String date;
    private Map<LocalDate, HolidaysDTO> holidayMap;

    public StartOfWeekDayDTO(String date) {
        this.date = date;
        this.holidayMap = new LinkedHashMap<>();
    }

    @JsonProperty(value = "holidays")
    public List<HolidaysDTO> getHolidays() {
        return holidayMap.values().stream().sorted().toList();
    }

    public void addHoliday(LocalDate localDate, HolidaysDTO holiday) {
        if (holidayMap.containsKey(localDate))
            holidayMap.get(localDate).addNames(holiday.getNames());
        else
            holidayMap.put(localDate, holiday);
    }

    @JsonProperty(value = "cellColorScheme")
    public String getCellColorScheme() {
        return holidayMap.size() > 1 ? ColorFill.holidayDark.getFillName() :
                ColorFill.holidayLight.getFillName();
    }

    public String toString() {
        return String.format("%s:%d:%s:%s", date, holidayMap.size(), getCellColorScheme(), holidayMap.values());
    }
}
