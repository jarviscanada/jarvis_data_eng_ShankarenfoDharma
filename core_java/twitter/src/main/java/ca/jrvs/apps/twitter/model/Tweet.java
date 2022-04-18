package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "created_at",
        "id",
        "id_str",
        "text",
        "entities",
        "coordinates",
        "retweet_count",
        "favorite_count",
        "favorited",
        "retweeted"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {
    @JsonProperty("created_at")
    private String created_at;
    @JsonProperty("id")
    private Long id;
    @JsonProperty("id_str")
    private String id_str;
    @JsonProperty("text")
    private String text;
    @JsonProperty("entities")
    private Collection<Entity> entities = null;
    @JsonProperty("coordinates")
    private Coordinate coordinates;
    @JsonProperty("retweet_count")
    private Integer retweet_count;
    @JsonProperty("favorite_count")
    private Integer favorite_count;
    @JsonProperty("favorited")
    private Boolean favorited;
    @JsonProperty("retweeted")
    private Boolean retweeted;

    @JsonProperty("created_at")
    public String getCreated_at() {
        return created_at;
    }

    @JsonProperty("created_at")
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @JsonProperty("id")
    public Long getId() { return id; }

    @JsonProperty("id")
    public void setId(Long id) { this.id = id; }

    @JsonProperty("id_str")
    public String getId_str() {
        return id_str;
    }

    @JsonProperty("id_str")
    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("entities")
    public Collection<Entity> getEntities() {
        return entities;
    }

    @JsonProperty("entities")
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @JsonProperty("coordinates")
    public Coordinate getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("retweet_count")
    public Integer getRetweet_count() {
        return retweet_count;
    }

    @JsonProperty("retweet_count")
    public void setRetweet_count(Integer retweet_count) {
        this.retweet_count = retweet_count;
    }

    @JsonProperty("favorite_count")
    public Integer getFavorite_count() {
        return favorite_count;
    }

    @JsonProperty("favorite_count")
    public void setFavorite_count(Integer favorite_count) {
        this.favorite_count = favorite_count;
    }

    @JsonProperty("favorited")
    public Boolean isFavorited() {
        return favorited;
    }

    @JsonProperty("favorited")
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    @JsonProperty("retweeted")
    public Boolean isRetweeted() {
        return retweeted;
    }

    @JsonProperty("retweeted")
    public void setRetweeted(Boolean retweeted) {
        this.retweeted = retweeted;
    }
}
