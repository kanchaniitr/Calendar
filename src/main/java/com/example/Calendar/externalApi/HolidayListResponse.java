package com.example.Calendar.externalApi;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@Getter
@Setter
public class HolidayListResponse {
    private List<HolidayWithName> holidays;

    public String toString() {
        return holidays.toString();
    }
}
