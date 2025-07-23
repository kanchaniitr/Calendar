package com.example.Calendar.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@NoArgsConstructor
@Getter
@Setter
public class DateDTO {
    private LocalDate localDate;
    private int date;
    private String day;
    private boolean isHoliday;
    private DateFillColor fillColor;

    public DateDTO(LocalDate localDate) {
        this.localDate = localDate;
        this.date = localDate.getDayOfMonth();
        this.day = localDate.getDayOfWeek().name();
        this.isHoliday = false;
        this.fillColor = DateFillColor.None;
    }

    public int getStartOfWeek() {
        return Math.max(1, date - localDate.getDayOfWeek().getValue() - 1);
    }

    public boolean isWeekend() {
        return localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ||
                localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

    public int getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public void setHoliday(boolean holiday) {
        isHoliday = holiday;
    }

    public DateFillColor getFillColor() {
        return fillColor;
    }

    public void setFillColor(DateFillColor fillColor) {
        this.fillColor = fillColor;
    }
}
