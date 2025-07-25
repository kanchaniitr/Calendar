package com.example.Calendar.externalApi;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class InternalDateResponse {
    private String iso;
    private DateTimeResponse datetime;

    public String getIso() {
        return iso;
    }

    public DateTimeResponse getDatetime() {
        return datetime;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void setDatetime(DateTimeResponse datetime) {
        this.datetime = datetime;
    }

    public String toString() {
        return datetime.toString();
    }
}
