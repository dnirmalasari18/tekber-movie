package com.mala.movie.Model;

import com.squareup.moshi.Json;

import java.util.List;

public class Episode {
    @Json(name = "id") private int id;
    @Json(name = "episodeName") private String episodeName;
    @Json(name = "director") private String director;
    @Json(name = "firstAired") private String firstAired;
    @Json(name = "filename") private String filename;
    @Json(name = "guestStars") private List<String> guestStarsList;
    @Json(name = "airedSeason") private int airedSeason;
    @Json(name = "siteRating") private double rating;

    public Episode() {

    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFirstAired() {
        return firstAired;
    }

    public void setFirstAired(String firstAired) {
        this.firstAired = firstAired;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getGuestStarsList() {
        return guestStarsList;
    }

    public void setGuestStarsList(List<String> guestStarsList) {
        this.guestStarsList = guestStarsList;
    }

    public int getAiredSeason() {
        return airedSeason;
    }

    public void setAiredSeason(int airedSeason) {
        this.airedSeason = airedSeason;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
