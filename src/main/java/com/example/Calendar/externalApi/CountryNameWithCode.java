package com.example.Calendar.externalApi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryNameWithCode {

    @JsonProperty(value = "country_name")
    private String name;
    @JsonProperty(value = "iso-3166")
    private String code;

    public String toString() {
        return String.format("[name: %s, code: %s]", name, code);
    }
}
