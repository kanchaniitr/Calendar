package com.example.Calendar.externalApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CountryNameWithCode {

    @JsonProperty(value = "country_name")
    private String name;
    @JsonProperty(value = "iso-3166")
    private String code;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return String.format("[name: %s, code: %s]", name, code);
    }
}
