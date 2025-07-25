package com.example.Calendar.externalApi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class InternalDateResponse {
    private String iso;
    private DateTimeResponse datetime;

    public String toString() {
        return datetime.toString();
    }
}
