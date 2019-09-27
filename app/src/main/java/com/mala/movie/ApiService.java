package com.mala.movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("series/{id}")
    Call<GetEpisodesResponse> getEpisodes(@Path("id") long id);

}
