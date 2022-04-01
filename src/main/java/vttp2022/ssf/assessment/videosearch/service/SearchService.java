package vttp2022.ssf.assessment.videosearch.service;

import java.util.List;
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

    private static final String URL = "https://api.rawg.io/api/games";

    public List<Game> search(String searchString, Integer count){
        
        String getGameResults = UriComponentsBuilder.fromUriString(URL)
            .queryParam("search", searchString)
            .queryParam("page_size", count)
            .queryParam("key",apiKey)
            .toUriString();
        
        RequestEntity<Void> req = RequestEntity.get(getGameResults)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);
        return GameUtil.create(resp.getBody()).getGames();
    }
    
}
