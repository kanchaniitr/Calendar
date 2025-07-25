package com.example.Calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HolidaysDTO {
    private String date;
    private String name;

    public String toString() {
        return String.format("%s-%s", date, name);
    }
}
