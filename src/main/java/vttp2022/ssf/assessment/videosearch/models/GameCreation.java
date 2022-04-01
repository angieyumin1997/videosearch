package vttp2022.ssf.assessment.videosearch.models;

import jakarta.json.JsonObject;

public class GameCreation {
    private String title;
    private Float rating;
    private String backgroundimage;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Float getRating() {
        return rating;
    }
    public void setRating(Float rating) {
        this.rating = rating;
    }
    public String getBackgroundimage() {
        return backgroundimage;
    }
    public void setBackgroundimage(String backgroundimage) {
        this.backgroundimage = backgroundimage;
    }

    public static GameCreation create(JsonObject o) {
        GameCreation gamecreation = new GameCreation();
        gamecreation.title = o.getString("name");
        gamecreation.rating = o.getJsonNumber("rating").bigDecimalValue().floatValue();
        gamecreation.backgroundimage = o.getString("background_image");
        
        return gamecreation;
    }
}
