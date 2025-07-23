package com.example.Calendar.externalApi;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class HolidayResponse {
    private HolidayListResponse response;

    public HolidayListResponse getResponse() {
        return response;
    }

    public void setResponse(HolidayListResponse response) {
        this.response = response;
    }
}
