package com.example.Calendar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class HolidaysDTO implements Comparable<HolidaysDTO> {
    private String date;
    private List<HolidayNameDTO> names;

    public HolidaysDTO(String date, HolidayNameDTO nameDTO) {
        this.date = date;
        this.names = new ArrayList<>();
        this.names.add(nameDTO);
    }

    @Override
    public int compareTo(HolidaysDTO o) {
        return this.date.compareTo(o.date);
    }

    public void addNames(List<HolidayNameDTO> names) {
        this.names.addAll(names);
    }

    public String toString() {
        return String.format("%s-%s", date, names);
    }
}
