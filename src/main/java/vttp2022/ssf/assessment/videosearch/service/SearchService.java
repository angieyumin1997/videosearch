package vttp2022.ssf.assessment.videosearch.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.ssf.assessment.videosearch.models.Game;
import vttp2022.ssf.assessment.videosearch.models.GameUtil;

public class SearchService {

    // set RAWG_API_KEY = the key
    @Value("${rawg.api.key}")
    private String apiKey;

    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != apiKey;
        System.out.println(">>>> API key set: " + hasKey);
    }

    private static final String URL = "https://api.rawg.io/api/games";

    public List<Game> search(String searchString, Integer count){
        
        String getGameResults = UriComponentsBuilder.fromUriString(URL)
            .queryParam("search", searchString)
            .queryParam("page_size", count)
            .queryParam("key","731439d59fbc4ce49cff91b824f9d96c")
            .toUriString();

        System.out.printf(">>> website: %s", getGameResults);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.getForEntity(getGameResults, String.class);
        return GameUtil.create(resp.getBody()).getGames();
    }
    
}
