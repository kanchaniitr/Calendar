package com.example.Calendar.dto;

import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.Month.*;

public enum QuarterOfYear {
    Jan_Mar(Arrays.asList(JANUARY, FEBRUARY, MARCH)),
    Apr_Jun(Arrays.asList(APRIL, MAY, JUNE)),
    Jul_Sep(Arrays.asList(JULY, AUGUST, SEPTEMBER)),
    Oct_Dec(Arrays.asList(OCTOBER, NOVEMBER, DECEMBER));

    private List<Month> monthList;

    QuarterOfYear(List<Month> monthList) {
        this.monthList = monthList;
    }

    public List<Month> getMonthList() {
        return monthList;
    }
}
