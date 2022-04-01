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

    private List<GameCreation> games;
    
    public List<GameCreation> getGames() {
        return games;
    }

    public void setGames(List<GameCreation> games) {
        this.games = games;
    }

    public static GameUtil create(String json){
        GameUtil u = new GameUtil();

        InputStream is = new ByteArrayInputStream(json.getBytes());
        JsonReader r = Json.createReader(is);
        JsonObject o = r.readObject();

        JsonArray a = o.getJsonArray("results");
        List <GameCreation> games = new LinkedList<>();
        
        for (int i = 0; i < a.size(); i++){
           
            games.add(GameCreation.create(a.getJsonObject(i)));
        }
        u.games = games;
        return u;
        

    } 


}
