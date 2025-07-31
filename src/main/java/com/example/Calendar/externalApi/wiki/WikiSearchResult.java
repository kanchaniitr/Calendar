package com.example.Calendar.externalApi.wiki;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class WikiSearchResult {
    private String title;
}
