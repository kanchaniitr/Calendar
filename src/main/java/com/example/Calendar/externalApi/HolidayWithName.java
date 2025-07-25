package com.example.Calendar.externalApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class HolidayWithName {

    private String name;
    private InternalDateResponse date;

    public String toString() {
        return String.format("[name: %s, Date: %s]", name, date);
    }
}
