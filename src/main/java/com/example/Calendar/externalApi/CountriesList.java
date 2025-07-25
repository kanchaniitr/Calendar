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
public class CountriesList {
    String url;
    private List<CountryNameWithCode> countries;
}
