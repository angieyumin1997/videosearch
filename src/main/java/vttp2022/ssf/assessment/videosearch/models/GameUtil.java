package vttp2022.ssf.assessment.videosearch.models;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.JsonValue;

import java.io.ByteArrayInputStream;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class GameUtil {

    private List<Game> games;
    
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public static GameUtil create(String json){
        GameUtil u = new GameUtil();
        Game g = new Game();

        InputStream is = new ByteArrayInputStream(json.getBytes());
        JsonReader r = Json.createReader(is);
        JsonObject o = r.readObject();

        JsonArray a = o.getJsonArray("results");
        List <Game> games = new LinkedList<>();
        
        for (int i = 0; i < a.size(); i++){
            
            String name = a.getJsonObject(i).getString("name");
            g.setName(name);

            Float rating = a.getJsonObject(i).getJsonNumber("rating").bigDecimalValue().floatValue();
            g.setRating(rating);

            String backgroundimage = a.getJsonObject(i).getString("background_image");
            g.setBackgroundImage(backgroundimage);

            games.add(g);
        }
        u.games = games;
        return u;
        

    } 


}
