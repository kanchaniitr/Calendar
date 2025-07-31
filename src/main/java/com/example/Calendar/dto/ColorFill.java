package com.example.Calendar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ColorFill {
    holidayLight("holiday-light"),
    holidayDark("holiday-dark");

    private String fillName;
}
