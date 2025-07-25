package com.example.Calendar.externalApi;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class DateTimeResponse {

    private int year;
    private int month;
    private int day;

    public String toString() {
        return String.format("%04d%02d%02d", year, month, day);
    }
}
