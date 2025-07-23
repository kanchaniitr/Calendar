package com.example.Calendar.externalApi;


import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
public class HolidayListResponse {
    private List<HolidayWithName> holidays;

    public List<HolidayWithName> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<HolidayWithName> date) {
        this.holidays = date;
    }

    public String toString() {
        return holidays.toString();
    }
}
