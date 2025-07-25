package com.example.Calendar.externalApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class HolidayResponse {
    private HolidayListResponse response;
}
