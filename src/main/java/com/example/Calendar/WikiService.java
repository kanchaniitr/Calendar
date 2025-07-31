package com.example.Calendar;

import com.example.Calendar.externalApi.CountriesResponse;
import com.example.Calendar.externalApi.wiki.WikiResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Setter
public class WikiService {
    private static final String baseURI = "https://en.wikipedia.org/w/api.php?action=query&list=search&format=json&srsearch=";

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    private final Map<String, Map<String, String>> wikiTokenCache;
    private final Map<String, String> genericTokenMap;
    private final Set<String> specialTerms;

    public WikiService() {
        this.wikiTokenCache = new ConcurrentHashMap<>();
        this.genericTokenMap = new ConcurrentHashMap<>();
        genericTokenMap.put("ramzan id", "Eid");
        this.specialTerms = new HashSet<>();
        specialTerms.add("independence day");
        specialTerms.add("republic day");
    }
    public String getWikiToken(String country, String actualTerm) {
        if (genericTokenMap.containsKey(actualTerm.toLowerCase()))
            return genericTokenMap.get(actualTerm);
        if (specialTerms.contains(actualTerm.toLowerCase())) {
            actualTerm += country;
        }
        wikiTokenCache.computeIfAbsent(country, l -> new ConcurrentHashMap<>());
        Map<String, String> wikiTokenMap = wikiTokenCache.get(country);
        String wikiToken = wikiTokenMap.getOrDefault(actualTerm, "");
        if (wikiToken.isEmpty()) {
            String url = baseURI + actualTerm;
            WikiResponse wikiResponse = restTemplate.getForObject(url, WikiResponse.class);
            if (wikiResponse != null && !wikiResponse.getQuery().getSearch().isEmpty()) {
                wikiToken = wikiResponse.getQuery().getSearch().get(0).getTitle();
                wikiTokenMap.put(actualTerm, wikiToken);
            }
        }
        return wikiToken;
    }
}
