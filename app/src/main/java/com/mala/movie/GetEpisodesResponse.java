package com.mala.movie;

import com.mala.movie.Model.Episode;
import com.squareup.moshi.Json;

import java.util.List;

public class GetEpisodesResponse {
    @Json(name = "data")
    List<Episode> episodes;

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
